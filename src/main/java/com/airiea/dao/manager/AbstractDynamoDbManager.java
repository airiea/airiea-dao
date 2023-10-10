package com.airiea.dao.manager;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.Objects;

abstract class AbstractDynamoDbManager<T> {

    protected void validateOnCreate(T item, DynamoDBMapper mapper) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Input can't be null.");
        }

        if (!Objects.isNull(mapper.load(item))) {
            throw new IllegalArgumentException("Item " + item + " already exists.");
        }

    }

    protected void validateOnDelete(T item, DynamoDBMapper mapper) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Input can't be null.");
        }

        if (Objects.isNull(mapper.load(item))) {
            throw new IllegalArgumentException("Item " + item + " not found.");
        }

    }

    protected void validateOnUpdate(T item, DynamoDBMapper mapper) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Input can't be null.");
        }

        if (Objects.isNull(mapper.load(item))) {
            throw new IllegalArgumentException("Item " + item + " not found.");
        }
    }

    protected void validateOnGetNotNull(T item, DynamoDBMapper mapper) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Input can't be null.");
        }

        if (Objects.isNull(mapper.load(item))) {
            throw new IllegalArgumentException("Item " + item + " not found.");
        }
    }

    protected void validateOnQuery(DynamoDBQueryExpression<T> expression, Integer limit) {
        if (Objects.isNull(expression)) {
            throw new IllegalArgumentException("Query expression can't be null.");
        }

        if (expression.getLimit() == null) {
            throw new IllegalArgumentException("Query expression limit can't be null.");
        }

        if (expression.getLimit() > limit) {
            throw new IllegalArgumentException("Query limit can't be more than " + limit);
        }
    }

}
