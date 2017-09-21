package crmOrder;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import src.OrderRepository;

/**
 * 与CRM订单接口
 * @author xilang
 *
 */
/**
 * @author xilang
 *
 */
public class crmOrderInterface {
	private final OrderRepository orderRepository = OrderRepository.getInstance();
	
	public void callInterface(String id){
		GeneralLocator services = new GeneralLocator();
		GeneralSoap11BindingStub binding;
		String responseValue = "";
		try {
			binding = (GeneralSoap11BindingStub) services.getgeneralHttpSoap11Endpoint();
			binding.setTimeout(30000);
			String queryString = "<usbdatas><item><id>"+id+"</id></item></usbdatas>";
			String sysSource = "BT001";
			responseValue = binding.getData(sysSource,queryString);
			if(responseValue != "" && responseValue != null){
				getReturnXml(responseValue,id);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 解析返回的值
	 * @param xml
	 * @return
	 * @throws DocumentException 
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public void getReturnXml(String xml,String id) {
		try{
			int start = xml.indexOf("&");
			int stop = xml.lastIndexOf(";")+1;
			xml = xml.substring(start, stop);
			xml = xml.replace("&lt;", '<'+"");
			xml = xml.replace("&gt;", '>'+"");
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			SAXReader saxreader = new SAXReader();
			String custId = "";
			String po_inst_id = "";
			String staff_id = "";
			String soDate = "";
			String pd_spec_id = "";
			String channel_nbr = "";
			String action_cd = "";
			String returnValue = "";
			String pd_inst_id = "";
			String serv_spec_id = "";
			String area_code = "";
			Document document = saxreader.read(source);
			List list = document.content();//.elementTextTrim("partyInfo");
			Iterator iter = list.iterator();
			if(iter.hasNext()){
				Element element = (Element) iter.next();
				channel_nbr = (element.attribute("agentNbr").getValue() == null) ? "" : element.attribute("agentNbr").getValue();
				soDate = (element.attribute("soDate").getValue() == null) ? "" : element.attribute("soDate").getValue();
				custId = (element.element("partyInfo").element("custId").getText() == null) ? "" : element.element("partyInfo").element("custId").getText();
				area_code = (element.attribute("soAreaCode").getValue() == null) ? "" : element.attribute("soAreaCode").getValue();
				area_code = changeCode(area_code);
				List offerAction = document.selectNodes("/OrderList/actionMsgs/offerAction");
				Iterator iter1 = offerAction.iterator();
				while(iter1.hasNext()){
					Element element1 = (Element) iter1.next();
					po_inst_id = (element1.attribute("offerId").getValue() == null) ? "" : element1.attribute("offerId").getValue();
					action_cd = (element1.attribute("boActionCd").getValue() == null) ? "" : element1.attribute("boActionCd").getValue();
					if(action_cd.equals("S1")){
						if(element1.element("staffInfo") != null){
							staff_id = (element1.element("staffInfo").element("staffId").getText() == null) ? "" : element1.element("staffInfo").element("staffId").getText();
						}
						if(element1.element("new") != null){
							if(element1.element("new").element("offerMembers") != null){
								pd_spec_id = (element1.element("new").element("offerMembers").element("offerMember").element("memberSpecId").getText() == null) ? "" : element1.element("new").element("offerMembers").element("offerMember").element("memberSpecId").getText();
								pd_inst_id = (element1.element("new").element("offerMembers").element("offerMember").element("memberId").getText() == null) ? "" : element1.element("new").element("offerMembers").element("offerMember").element("memberId").getText();
								returnValue = pd_inst_id + "|" + custId + "|" + po_inst_id + "|" + pd_spec_id + "|" + "" + "|" + action_cd + "|" + soDate + "|" + staff_id + "|" + soDate + "|" + channel_nbr + "|" + "" + "|" + area_code;
								orderRepository.offer(returnValue);
							}
						}
					}else{
						continue;
					}
				}
				List prodAction = document.selectNodes("/OrderList/actionMsgs/prodAction");
				Iterator iter2 = prodAction.iterator();
				while(iter2.hasNext()){
					Element element2 = (Element) iter2.next();
					pd_inst_id = (element2.attribute("prodId").getValue() == null) ? "" : element2.attribute("prodId").getValue();
					action_cd = (element2.attribute("boActionCd").getValue() == null) ? "" : element2.attribute("boActionCd").getValue();
					pd_spec_id = (element2.attribute("prodSpecId").getValue() == null) ? "" : element2.attribute("prodSpecId").getValue();
					if(element2.element("staffInfo") != null){
						staff_id = (element2.element("staffInfo").element("staffId").getText() == null) ? "" : element2.element("staffInfo").element("staffId").getText();
					}
					if(action_cd.equals("1")){
						returnValue = pd_inst_id + "|" + custId + "|" + "" + "|" + pd_spec_id + "|" + "" + "|" + action_cd + "|" + soDate + "|" + staff_id + "|" + soDate + "|" + channel_nbr + "|" + "" + "|" + area_code;
						orderRepository.offer(returnValue);
					}else if(action_cd.equals("7")){
						if(element2.element("new") != null){
							if(element2.element("new").element("prodServ") != null){
								if(element2.element("new").element("prodServ").element("serv") != null){
									if(element2.element("new").element("prodServ").element("serv").element("servInfo") != null){
										serv_spec_id = (element2.element("new").element("prodServ").element("serv").element("servInfo").element("servSpecId").getText() == null) ? "" : element2.element("new").element("prodServ").element("serv").element("servInfo").element("servSpecId").getText();
										returnValue = pd_inst_id + "|" + custId + "|" + po_inst_id + "|" + pd_spec_id + "|" + serv_spec_id + "|" + action_cd + "|" + soDate + "|" + staff_id + "|" + soDate + "|" + channel_nbr + "|" + "" + "|" + area_code;
										orderRepository.offer(returnValue);
									}
								}
							}
						}
					}else{
						continue;
					}
				}
			}
		}catch(DocumentException e){
			e.printStackTrace();
		}
	}

	public static String changeCode(String areaCode){
		switch (areaCode){
			case "0555": return "8";
			case "025": return "250";
			case "0510": return "510";
			case "0511": return "511";
			case "0512": return "512";
			case "0513": return "513";
			case "0514": return "514";
			case "0515": return "515";
			case "0516": return "516";
			case "0517": return "517";
			case "0518": return "518";
			case "0519": return "519";
			case "0523": return "523";
			case "0527": return "527";
			default: return "8";
		}
	}
}
