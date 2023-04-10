/**
 * 
 */
package hashimotonet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hashimotonet.bean.RequestBean;
import hashimotonet.service.UploadService;

/**
 * @author hashi
 *
 */
@Controller
public class UploadController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;

	/**
	 * 画像データ操作サービス
	 */
	private final UploadService service;
	
	/**
	 * Logger.
	 */
    private Logger log = LogManager.getLogger(UploadController.class);

    /**
	 * DIコンストラクタ
	 * 
	 * @param service
	 */
	@Autowired
	public UploadController(UploadService service) {
		this.service = service;
	}
	
	/**
	 * デフォルトコンストラクタ
	 */
	public UploadController() {
		super();
		this.service = new UploadService();
	}

	@RequestMapping(path="/Upload/browser",
			produces = "application/json", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public String index(@RequestBody RequestBean reqBean) {
		log.debug("reqBean : \r\n" + reqBean);
		try {
			service.execute(request, reqBean);
		} catch (Exception e) {
			log.catching(e);
		}
		
		return "photo";
	}
	
	@RequestMapping(path="/Upload",
					produces = "application/json", 
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	@ResponseBody
	public String index() {
		log.debug("index()");
		try {
			service.execute(request);
			//response.getWriter().println("success");
		} catch (Exception e) {
			log.catching(e);
		}
		
		return "success";
	}
}