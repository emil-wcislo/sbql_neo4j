package pl.wcislo.neo4j_sbql.visitor;

import org.neo4j.graphdb.GraphDatabaseService;

import pl.wcislo.neo4j_sbql.ast.binary.DivideExpression;
import pl.wcislo.neo4j_sbql.ast.binary.DotExpression;
import pl.wcislo.neo4j_sbql.ast.binary.EqualsExpression;
import pl.wcislo.neo4j_sbql.ast.binary.GreaterThanExpression;
import pl.wcislo.neo4j_sbql.ast.binary.LessThanExpression;
import pl.wcislo.neo4j_sbql.ast.binary.MinusExpression;
import pl.wcislo.neo4j_sbql.ast.binary.MultiplyExpression;
import pl.wcislo.neo4j_sbql.ast.binary.PlusExpression;
import pl.wcislo.neo4j_sbql.ast.binary.WhereExpression;
import pl.wcislo.neo4j_sbql.ast.terminal.BooleanTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.DoubleTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.IntegerTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.NameTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.StringTerminal;
import pl.wcislo.neo4j_sbql.envs.ENVS;
import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.collection.BagResult;
import pl.wcislo.neo4j_sbql.result.single.BooleanResult;
import pl.wcislo.neo4j_sbql.result.single.DoubleResult;
import pl.wcislo.neo4j_sbql.result.single.IntegerResult;
import pl.wcislo.neo4j_sbql.result.single.SimpleValueResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;
import pl.wcislo.neo4j_sbql.result.single.StringResult;
import pl.wcislo.neo4j_sbql.visitor.utils.CoercionUtils;
import pl.wcislo.neo4j_sbql.visitor.utils.SimpleValueUtils;

public class Interpreter implements ASTVisitor {
	private GraphDatabaseService db;
	private ENVS envs;
	private QRES qres;
	
	public Interpreter(GraphDatabaseService db) {
		envs = new ENVS(db);
		qres = new QRES();
	}
	
	public AbstractQueryResult getResult() {
		if(qres.isEmpty()) {
			return null;
		} else {
			return qres.pop();
		}
	}

	@Override
	public void visitDivideExpression(DivideExpression expr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitDotExpression(DotExpression expr) {
		BagResult dotRes = new BagResult();
		expr.getLeftExpr().accept(this);
		BagResult leftBag = CoercionUtils.coercionToBag(qres.pop());
		for(SingleResult sr : leftBag.getElements()) {
			envs.nested(sr);
			expr.getRightExpr().accept(this);
			BagResult rightRes = CoercionUtils.coercionToBag(qres.pop());
			dotRes.getElements().addAll(rightRes.getElements());
			envs.pop();
		}
		qres.push(dotRes);
	}

	@Override
	public void visitEqualsExpression(EqualsExpression expr) {
		expr.getLeftExpr().accept(this);
		SingleResult leftRes = CoercionUtils.coercionToSingleResult(qres.pop());
		expr.getRightExpr().accept(this);
		SingleResult rightRes = CoercionUtils.coercionToSingleResult(qres.pop());
		boolean res = leftRes.equals(rightRes);
		BooleanResult bRes = new BooleanResult(res);
		qres.push(bRes);
	}

	@Override
	public void visitGreaterThanExpression(GreaterThanExpression expr) {
		expr.getLeftExpr().accept(this);
		SingleResult leftRes = CoercionUtils.coercionToSingleResult(qres.pop());
		expr.getRightExpr().accept(this);
		SingleResult rightRes = CoercionUtils.coercionToSingleResult(qres.pop());
		int compareResult = SimpleValueUtils.compareResults(leftRes, rightRes);
		boolean res = compareResult > 0;
		BooleanResult br = new BooleanResult(res);
		qres.push(br);
	}

	@Override
	public void visitLessThanExpression(LessThanExpression expr) {
		expr.getLeftExpr().accept(this);
		SingleResult leftRes = CoercionUtils.coercionToSingleResult(qres.pop());
		expr.getRightExpr().accept(this);
		SingleResult rightRes = CoercionUtils.coercionToSingleResult(qres.pop());
		int compareResult = SimpleValueUtils.compareResults(leftRes, rightRes);
		boolean res = compareResult < 0;
		BooleanResult br = new BooleanResult(res);
		qres.push(br);
	}

	@Override
	public void visitMinusExpression(MinusExpression expr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMultiplyExpression(MultiplyExpression expr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitPlusExpression(PlusExpression expr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitWhereExpression(WhereExpression expr) {
		BagResult whereRes = new BagResult();
		expr.getLeftExpr().accept(this);
		BagResult leftBag = CoercionUtils.coercionToBag(qres.pop());
		for(SingleResult sr : leftBag.getElements()) {
			envs.nested(sr);
			expr.getRightExpr().accept(this);
			SingleResult rightRes = CoercionUtils.coercionToSingleResult(qres.pop());
			if(rightRes instanceof BooleanResult) {
				BooleanResult br = (BooleanResult) rightRes;
				if(br.getValue()) {
					whereRes.getElements().add(sr);
				}
			} else {
				continue;
			}
			envs.pop();
		}
		qres.push(whereRes);
	}

	@Override
	public void visitBooleanTerminal(BooleanTerminal expr) {
		BooleanResult br = new BooleanResult(expr.getValue());
		qres.push(br);
	}

	@Override
	public void visitDoubleTerminal(DoubleTerminal expr) {
		DoubleResult dr = new DoubleResult(expr.getValue());
		qres.push(dr);
	}

	@Override
	public void visitIntegerTerminal(IntegerTerminal expr) {
		IntegerResult ir = new IntegerResult(expr.getValue());
		qres.push(ir);
	}

	@Override
	public void visitNameTerminal(NameTerminal expr) {
		BagResult res = envs.bind(expr.getName());
		qres.push(res);
	}

	@Override
	public void visitStringTerminal(StringTerminal expr) {
		StringResult sr = new StringResult(expr.getValue());
		qres.push(sr);
	}

}
