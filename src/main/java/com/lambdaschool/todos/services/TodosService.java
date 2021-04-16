package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todos;
import org.springframework.transaction.annotation.Transactional;

public interface TodosService
{
    void markComplete(long todoid);
    Todos findTodosById(Long id);

    @Transactional
    Todos update(Todos todos, Long id);
}
