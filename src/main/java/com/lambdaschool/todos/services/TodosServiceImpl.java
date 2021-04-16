package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "TodosService")
public class TodosServiceImpl implements TodosService {

    @Autowired
    private TodosRepository todosRepo;

    @Override
    public void markComplete(long todoid) {
        Todos todos = findTodosById(todoid);
        if(!todos.isCompleted()){todos.setCompleted(true);}
    }

    @Override
    public Todos findTodosById(Long id) {
        return todosRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Todo " + id + " Not Found!"));
    }


    @Transactional
    @Override
    public Todos update(Todos todos, Long id){

        Todos currentTodos = findTodosById(id);

        if(todos.getDescription() != null){currentTodos.setDescription(todos.getDescription());}
        if(todos.isCompleted() || !todos.isCompleted()){currentTodos.setCompleted(todos.isCompleted());}
        if(todos.getUser() != null){currentTodos.setUser(todos.getUser());}

        return todosRepo.save(currentTodos);
    }
}
