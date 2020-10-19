package com.gj.es;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gj.es.model.DateWrapper;
import com.gj.es.model.WorkDTO;
import com.gj.es.repositories.IWorkRepository;


@SpringBootTest
public class EsApplicationTests {

	@Autowired
	private  ElasticsearchOperations elasticsearchOperations;
	
	@Autowired
	IWorkRepository iWorkRepository;
	
	@Test
	public void doit() {
		String commitInfo ="3bfa410dfad36100388a92555a4cc0b627c25d21 Merge pull request #12 from gjrocks/TestWin";
			//8fe0ec0c4f8a5a89cdd9b2db507492148d41318c dddd"
		assertThat(commitInfo).isNotNull();
		assertThat(commitInfo.split(" ")).hasSizeGreaterThan(1);
		assertThat(commitInfo.split(" ")[0]).isEqualTo("3bfa410dfad36100388a92555a4cc0b627c25d21");
		
			
	}
	//@Test
	void contextLoads() {
		assertNotNull(elasticsearchOperations);
		WorkDTO wk = elasticsearchOperations.get("12", WorkDTO.class); 
		//get("12", Work.class);
		System.out.println(wk);
		wk.setWorkDesc("Ganesh updated");
		String format="yyyy-MM-dd'T'HH:mm:ss";
		Date wkinception=DateWrapper.convertStringToDate(wk.getWork_inception_date(), format);
		wkinception=DateWrapper.addDays(wkinception, 3);
		wk.setWork_inception_date(DateWrapper.convertDateToString(wkinception, format));
		//elasticsearchOperations.g
		//elasticsearchOperations.save(wk);
			      //.queryForObject(GetQuery.getById("12"), Work.class);
		WorkDTO g=iWorkRepository.findByWorkID("US78873");
		g.setWorkDesc(g.getWorkDesc()+"demo");
		System.out.println(g);
		iWorkRepository.save(g);
		
	}
	
	//@Test
	public void updateWork() {
		String workID="US79901";
		WorkDTO work=iWorkRepository.findByWorkID(workID);
		assertThat(work).isNotNull()
		                .has(new Condition<WorkDTO>(wrk->wrk.getWorkID().equals(workID),"Validate the work ID",work));
		
		System.out.println(work.getWork_inception_date());
		String format="yyyy-MM-dd'T'HH:mm:ss";
		Date wkinception=DateWrapper.convertStringToDate(work.getWork_inception_date(), format);
		wkinception=DateWrapper.addDays(wkinception, 3);
		work.setDevl_deploy_date(DateWrapper.convertDateToString(wkinception, format));
		iWorkRepository.save(work);
	}
	
	@Test
	void addNewWorkItem() throws Exception{
		assertNotNull(elasticsearchOperations);
		Resource stateFile = new ClassPathResource("data.json");
		assertThat(stateFile).isNotNull();
		assertThat(stateFile.getFile()).isNotNull().isFile();
		ObjectMapper mapper = new ObjectMapper();
        WorkDTO[] work=mapper.readValue(stateFile.getFile(), WorkDTO[].class);
        assertThat(work).isNotNull().hasSize(6);
      //  assertThat(work.getWorkID()).isEqualTo("US79902");
        for(int i=0;i<work.length;i++) 
        iWorkRepository.save(work[i]);
        
				//JSON file to Java object
				//AlfaMessage[] obj = mapper.readValue(stateFile.getFile(), AlfaMessage[].class);
				//System.out.println(obj);
		/*WorkDTO wk = elasticsearchOperations.get("12", WorkDTO.class); 
		//get("12", Work.class);
		System.out.println(wk);
		wk.setWorkDesc("Ganesh updated");
		String format="yyyy-MM-dd'T'HH:mm:ss";
		Date wkinception=DateWrapper.convertStringToDate(wk.getWork_inception_date(), format);
		wkinception=DateWrapper.addDays(wkinception, 3);
		wk.setWork_inception_date(DateWrapper.convertDateToString(wkinception, format));
		//elasticsearchOperations.g
		//elasticsearchOperations.save(wk);
			      //.queryForObject(GetQuery.getById("12"), Work.class);
		WorkDTO g=iWorkRepository.findByWorkID("US78873");
		g.setWorkDesc(g.getWorkDesc()+"demo");
		System.out.println(g);
		iWorkRepository.save(g);*/
		
	}

}
