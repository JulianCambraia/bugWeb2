package br.com.k19.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.k19.entities.Bug;
import br.com.k19.entities.Project;
import br.com.k19.sessionbeans.BugRepository;
import br.com.k19.sessionbeans.ProjectRepository;

@ManagedBean
@RequestScoped
public class BugMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8604060980166114587L;

	@Inject
	private BugRepository bugRepository;
	
	@Inject
	private ProjectRepository projectRepository;
	
	private Long projectId;
	
	private Bug bug = new Bug();
	
	private List<Bug> bugs;
	
	public void save() {
		Project project = this.projectRepository.findById(this.projectId);
		this.bug.setProject(project);
		if (this.getBug().getId() == null) {
			this.bugRepository.add(this.getBug());
		} else {
			this.bugRepository.edit(this.getBug());
		}
		this.bug = new Bug();
		this.bugs = null;
	}
	
	public void delete(Long id) {
		this.bugRepository.removeById(id);
		this.setBugs(null);
	}
	
	public void preparedEdit(Long id) {
		this.bug = this.bugRepository.findById(id);
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public List<Bug> getBugs() {
		if (this.bugs == null) {
			this.bugs = this.bugRepository.findAll();
		}
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
