package com.gj.es.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gj.es.model.WorkDTO;
import com.gj.es.model.WorkGit;
import com.gj.es.repositories.IWorkRepository;
/**
 * 
 * @author cqktss0
 *  you can add a report email to the team on entire record, notification on the breach of the SLA.
 */
@RestController
@RequestMapping("/alm")
public class AlmController {

	@Autowired
	private  ElasticsearchOperations elasticsearchOperations;
	
	@Autowired
	IWorkRepository iWorkRepository;
	
	@GetMapping("/ping")
	public @ResponseBody String ping() {
		return "ok";
	}
	
	@PostMapping("/work")
	public void addWork(@RequestBody WorkGit workGit) {
		System.out.println("WorkGit :"+ workGit);
		if(workGit!=null && workGit.getWorkIDs()!=null) {
			String workId=workGit.getWorkIDs();
			WorkDTO work=iWorkRepository.findByWorkID(workId);
			if(work !=null) {
				if(workGit.getBranch()!=null && workGit.getBranch().equalsIgnoreCase("devl")) {
					work.setDevl_deploy_date(workGit.getExDate());
					
				}
				if(workGit.getBranch()!=null && workGit.getBranch().equalsIgnoreCase("qual")) {
					work.setQual_deploy_date(workGit.getExDate());
					
				}
				if(workGit.getBranch()!=null && workGit.getBranch().equalsIgnoreCase("master")) {
					work.setProd_deploy_date(workGit.getExDate());
					
				}
				if(workGit.getCommitInfo() !=null) {
					String commitInfo=workGit.getCommitInfo();
					String commitID=commitInfo.split(" ")[0];
					work.setCommitId(commitID);
				}
				work.setGitRepoName(workGit.getGitRepoName());
				System.out.println(work);
				iWorkRepository.save(work);	
			}
		
			
		}
	}
	
}
