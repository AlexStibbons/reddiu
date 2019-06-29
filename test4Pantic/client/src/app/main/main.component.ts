import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../services/project.service';
import { PageC, Project } from '../common.models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  // projects
  page: PageC;
  projects: Project[];

  // search
  nameSearch: string;

  // edit or add project
  modifyProject: Project;

  constructor(private projectService: ProjectService,
              private router: Router,
              private authentication: AuthenticationService) {

    this.nameSearch = '';
    this.modifyProject = {
      id: -1,
      name: '',
      description: '',
      readme: ''
    }
   }

  ngOnInit() {
    this.getProjects();
  }

  clearSearch() {
    this.nameSearch = '';
    this.getProjects();
  }

  getProjects() {
    this.projectService.getProjects(0, this.nameSearch).subscribe(
      (res: PageC) => {
        this.page = res;
        this.projects = res.content;
      }
    );
  }

  prev(){
    this.projectService.getProjects(this.page.index - 1, this.nameSearch).subscribe(
      (res: PageC) => {
        this.page = res;
        this.projects = res.content;
      }
    );
  }

  next(){
    this.projectService.getProjects(this.page.index + 1, this.nameSearch).subscribe(
      (res: PageC) => {
        this.page = res;
        this.projects = res.content;
      }
    );
  }

  readMore(id: number){
    this.router.navigate([`/project/${id}`]);
  }

  loggedIn(){
    return this.authentication.isLoggedIn();
  }

  editProject(project: Project) {
    this.modifyProject = project;
  }

  modify() {
    if (this.modifyProject.id > -1) {
      this.projectService.editProject(this.modifyProject.id, this.modifyProject).subscribe(
        (res: Project) => {
          this.getProjects();
          this.cancelMod();
        }
      );
    } else {
      if (this.modifyProject.name.length > 0) {
        this.projectService.addProject(this.modifyProject).subscribe(
          (res: Project) => {
            this.getProjects();
            this.cancelMod();
          }
        );
      }
    }
  }

  cancelMod() {
    this.modifyProject = {
      id: -1,
      name: '',
      description: '',
      readme: ''
    }
  }

  deleteProject(project: Project) {
    this.projectService.deleteProject(project.id).subscribe(
      () => {
        this.getProjects();
      }
    );
  }

}
