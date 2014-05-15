package br.com.k19.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.k19.entities.Project;
import br.com.k19.sessionbeans.ProjectRepository;

@ManagedBean
@RequestScoped
public class ProjectMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7011670224862633428L;

	@Inject
	private ProjectRepository projectRepository;
	
	private Project project = new Project();
	
	private List<Project> projects;

	public void save() {
		if (this.getProject().getId() == null) {
			this.projectRepository.add(this.getProject());
		} else {
			this.projectRepository.edit(this.getProject());
		}
		this.project = new Project();
		this.projects = null;
	}
	
	public void delete(Long id) {
		this.projectRepository.removeById(id);
		this.projects = null;
	}
	
	public void preparedEdit(Long id) {
		this.project = this.projectRepository.findById(id);
	}
	
	public Project getProject() {
		return project;
	}
	
	public List<Project> getProjects() {
		if (this.projects == null) {
			this.projects = this.projectRepository.findAll();
		}
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public ProjectRepository getProjectRepository() {
		return projectRepository;
	}

	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
}
