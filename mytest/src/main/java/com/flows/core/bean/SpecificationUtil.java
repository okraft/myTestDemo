package com.flows.core.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtil<T> {
	public enum Operator {
		EQ, NEQ, LIKE, GT, LT, GTE, LTE, IN
	}

	public Specification<T> buildSpecification(final List<SearchParam> params) {
		return new Specification<T>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (!params.isEmpty()) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					for (Iterator<SearchParam> iterator = params.iterator(); iterator.hasNext();) {
						SearchParam filter = iterator.next();
						// nested path translate,
						Path expression = root.get(filter.getFieldName());
						// logic operator
						switch (Operator.valueOf(filter.getOperator())) {
						case EQ:
							predicates.add(builder.equal(expression, filter.getObjectValue()));
							break;
						case NEQ:
							predicates.add(builder.notEqual(expression, filter.getObjectValue()));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.getObjectValue() + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.getObjectValue()));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.getObjectValue()));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getObjectValue()));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getObjectValue()));
							break;
						case IN:
							Object obj = filter.getObjectValue();
							if(null == obj || obj.equals("")) {
								break;
							}else {
								Collection c = (Collection) filter.getObjectValue();
								//CriteriaBuilder IN查询
//								Predicate p = builder.in(expression).value(c);//錯誤
								Predicate p = expression.in(c);
								predicates.add(p);
							}
						}
					}
					if (predicates.size() > 0) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}
				return builder.conjunction();
			}
		};
	}
	
	/*public enum Op {
		equal, NEQ, contains, GT, LT, GTE, LTE, IN
	}
	
	public Specification<T> buildSpecificationFilter(final List<FilterParam> params) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (!params.isEmpty()) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					for (Iterator<FilterParam> iterator = params.iterator(); iterator.hasNext();) {
						FilterParam filter = iterator.next();
						// nested path translate,
						Path expression = root.get(filter.getField());
						// logic operator
						switch (Op.valueOf(filter.getOp())) {
						case equal:
							predicates.add(builder.equal(expression, filter.getObjectValue()));
							break;
						case NEQ:
							predicates.add(builder.notEqual(expression, filter.getObjectValue()));
							break;
						case contains:
							predicates.add(builder.like(expression, "%" + filter.getObjectValue() + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.getObjectValue()));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.getObjectValue()));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getObjectValue()));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getObjectValue()));
							break;
						case IN:
							Object obj = filter.getObjectValue();
							if(null == obj || obj.equals("")) {
								break;
							}else {
								Collection c = (Collection) filter.getObjectValue();
								//CriteriaBuilder IN查询
//								Predicate p = builder.in(expression).value(c);//錯誤
								Predicate p = expression.in(c);
								predicates.add(p);
							}
						}
					}
					if (predicates.size() > 0) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}
				return builder.conjunction();
			}
		};
	}*/
}
