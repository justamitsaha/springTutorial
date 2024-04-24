package com.saha.amit.controller;

import com.saha.amit.entity.relationShip.manyToMany.Programmer;
import com.saha.amit.entity.relationShip.manyToMany.Project;
import com.saha.amit.model.ProgrammerForProjectModel;
import com.saha.amit.model.ProgrammerModel;
import com.saha.amit.model.ProjectModel;
import com.saha.amit.repository.ProgrammerRepository;
import com.saha.amit.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/programmerCustomer")
public class ManyToManyController {

    @Autowired
    ProgrammerRepository programmerRepository;
    @Autowired
    ProjectRepository projectRepository;

    @PostMapping("/createProgrammer")
    public ResponseEntity<ProgrammerModel> createProgrammer(@RequestBody ProgrammerModel programmerModel) {
        HashSet<Project> projects = new HashSet<>();
        Random random = new Random();
        programmerModel.getProjectForProgrammerModels().forEach(projectForProgrammerModel -> {
            Project project = new Project();
            project.setName(projectForProgrammerModel.getProjectName());

            int id = random.nextInt(1000, 20000);
            project.setId(id);
            projects.add(project);
            projectForProgrammerModel.setId(id);
        });
        int id = random.nextInt(1000, 20000);
        Programmer programmer = Programmer.builder()
                .name(programmerModel.getProgrammerName())
                .salary(programmerModel.getSalary())
                .id(id)
                .projects(projects)
                .build();
        programmerModel.setId(id);
        programmerRepository.save(programmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(programmerModel);
    }

    @PostMapping("/createProject")
    public ResponseEntity<ProjectModel> createProject(@RequestBody ProjectModel projectModel) {
        Set<Programmer> programmerSet = new HashSet<>();
        Random random = new Random();
        projectModel.getProgrammerForProjectModels().forEach(programmerForProjectModel -> {
            Programmer programmer = Programmer.builder()
                    .id(random.nextInt(1000, 20000))
                    .name(programmerForProjectModel.getProgrammerName())
                    .salary(programmerForProjectModel.getSalary())
                    .build();
            programmerSet.add(programmer);
        });

        int id = random.nextInt(1000, 20000);
        Project project = Project.builder()
                .id(id)
                .name(projectModel.getProjectName())
                .programmers(programmerSet)
                .build();
        projectRepository.save(project);
        projectModel.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectModel);
    }
}
