package site.chronos.chronos;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import site.chronos.constant.CommonConstants;
import site.chronos.entity.Question;
import springfox.documentation.staticdocs.SwaggerResultHandler;
import springfox.documentation.swagger2.web.Swagger2Controller;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ChronosApplicationTests {

	 private String snippetDir = "target/generated-snippets";
	 private String outputDir = "target/asciidoc";
	 
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @After
	    public void Test() throws Exception{
	        // 得到swagger.json,写入outputDir目录中
	        mockMvc.perform(RestDocumentationRequestBuilders.get(Swagger2Controller.DEFAULT_URL).accept(MediaType.APPLICATION_JSON))
	                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
	                .andExpect(status().isOk())
	                .andReturn();

	        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
	        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
	        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
	                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
	                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
	                .withExamples(snippetDir)
	                .build()
	                .intoFolder(outputDir);// 输出
	    }
	@Test
	public void contextLoads() throws Exception {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("phone", "15869007707");
		map.add("pass", "15869007707");
		 mockMvc.perform(put("/u/login").params(map)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("login", preprocessResponse(prettyPrint())));
		 
		 //根据问题查询评论
		 mockMvc.perform(get("/c/question").param("questionId","0E9F0D5DD79A4AD9BE14DB125592861C")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("question", preprocessResponse(prettyPrint())));
//		 根据Comment获取子评论Comment
		 mockMvc.perform(get("/c/1")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("getCommentByCommentId", preprocessResponse(prettyPrint())));
		 
		//根据ID获取问题
		 mockMvc.perform(get("/q/0E9F0D5DD79A4AD9BE14DB125592861C")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("question", preprocessResponse(prettyPrint())));
		
		 //添加问题addQuestion
		Question question = new Question();
		question.setTitle("mockMvc测试标题");
		question.setQuestion("mockMvc测试内容");
		question.setUserId("F00D6C67D7704AE2B9163945EC56BCC4");
		mockMvc.perform(RestDocumentationRequestBuilders.put("/q/addQuestion").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
					.content(CommonConstants.GSONIGNORENULL.toJson(question))
//					.session((MockHttpSession)getLoginSession()) 
	                .accept(MediaType.APPLICATION_JSON))
//        			.andDo(print())
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("addQuestion", preprocessResponse(prettyPrint())));
		
		
		//置顶问题
		mockMvc.perform(RestDocumentationRequestBuilders.put("/q/1CAB7AB2A27E45F694A9D39925E0DDAC").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//					.content(CommonConstants.GSONIGNORENULL.toJson(question))
//					.session((MockHttpSession)getLoginSession()) 
	                .accept(MediaType.APPLICATION_JSON))
//        			.andDo(print())
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("sortQuestion", preprocessResponse(prettyPrint())));
		
		//查询问题
		
		mockMvc.perform(RestDocumentationRequestBuilders.put("/q/selectQuestion").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//				.content(CommonConstants.GSONIGNORENULL.toJson(question))
//				.session((MockHttpSession)getLoginSession()) 
                .accept(MediaType.APPLICATION_JSON))
//    			.andDo(print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("selectQuestion", preprocessResponse(prettyPrint())));
	
		//提交问题审核
		Question question1 = new Question();
		question1.setTitle("mockMvc review测试标题");
		question1.setQuestion("mockMvc review测试内容");
		question1.setUserId("F00D6C67D7704AE2B9163945EC56BCC4");
		mockMvc.perform(RestDocumentationRequestBuilders.put("/r/question").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
					.content(CommonConstants.GSONIGNORENULL.toJson(question1))
//					.session((MockHttpSession)getLoginSession()) 
	                .accept(MediaType.APPLICATION_JSON))
//        			.andDo(print())
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("/r/question", preprocessResponse(prettyPrint())));

		
		//查询所有审核
		mockMvc.perform(RestDocumentationRequestBuilders.get("/r/selectReview").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcRestDocumentation.document("/r/question", preprocessResponse(prettyPrint())));

		
			//查询自己的待审核问题
				mockMvc.perform(RestDocumentationRequestBuilders.get("/r/user/selectReview/F00D6C67D7704AE2B9163945EC56BCC4").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("/r/user/selectReviewn", preprocessResponse(prettyPrint())));

		//审核完毕  通过或者拒绝
		MultiValueMap<String, String> map1 = new LinkedMultiValueMap<String, String>();
		map1.add("reviewId", "1");
		map1.add("status", "10");
				mockMvc.perform(RestDocumentationRequestBuilders.put("/r/reviewOver").params(map1)
						.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
		                .accept(MediaType.APPLICATION_JSON))
		                .andExpect(status().isOk())
		                .andDo(MockMvcRestDocumentation.document("/r/reviewOver", preprocessResponse(prettyPrint())));
	}
	
	   /** 
     * 获取登入信息session 
     * @return 
     * @throws Exception 
     */  
    private HttpSession getLoginSession() throws Exception{  
        // mock request get login session  
        // url = /xxx/xxx/{username}/{password} 
    	MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("phone", "15869007707");
		map.add("pass", "15869007707");
        MvcResult result = this.mockMvc  
                                .perform((put("/u/login").params(map)))  
                                .andExpect(status().isOk()) 
//                                .andDo(print())
                                .andReturn();  
        HttpSession session = result.getRequest().getSession();  
        return session;
    } 

}
