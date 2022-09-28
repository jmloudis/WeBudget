package com.xpanxion.capstone.controller;

import com.xpanxion.capstone.model.Goal;
import com.xpanxion.capstone.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalController {

    private GoalRepository goalRepository;

    // dependency injection  > field injection (@Autowired)
    public GoalController(GoalRepository goalRepository){
        this.goalRepository = goalRepository;
    }

    @GetMapping("/goals")
    public List<Goal> listGoals(){
        return this.goalRepository.findAll();
    }

    @GetMapping("/goal/{id}")
    public Goal listGoal(@PathVariable Long id){
        return this.goalRepository.findById(id).get();
    }

    // link to user id
    @PostMapping("/create-goal")
    public Goal createGoal(@RequestBody Goal goal){
        return this.goalRepository.save(goal);
    }

    @DeleteMapping("/delete-goal/{id}")
    public void deleteGoal(@PathVariable Long id){
        Goal goal = this.goalRepository.findById(id).get();
        this.goalRepository.deleteById(id);
    }

    // link to user id
    @PutMapping("/update-goal/{id}")
    public Goal updateGoal(@RequestBody Goal inputGoal, @PathVariable Long id){
        Goal goal = this.goalRepository.findById(id).get();
        goal.setGoalAmount(inputGoal.getGoalAmount());
        goal.setSaveAmount(inputGoal.getSaveAmount());
        goal.setTime_in_months(inputGoal.getTime_in_months());
        Goal updatedGoal = this.goalRepository.save(goal);
        return updatedGoal;
    }
}
