package br.ufes;

import java.time.LocalDate;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class TaskAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(
            ArgumentsAccessor accessor,
            ParameterContext context
    ) throws ArgumentsAggregationException {

        return new Task(
                accessor.getString(0),
                accessor.getString(1),
                accessor.get(2, LocalDate.class)
        );
    }
}