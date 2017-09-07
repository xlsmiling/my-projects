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
		//	System.out.println("---------"+questXml);
			//System.out.println(id);
			responseValue = binding.getData(sysSource,queryString);
			//System.out.println("---------"+responseValue);
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
			// 创建一个新的SAXBuilder
			//SAXBuilder sb = new SAXBuilder();
			SAXReader saxreader = new SAXReader();
			String custId = "";
			String po_inst_id = "";
			String staff_id = "";
			String soDate = "";
			String offer_spec_id = "";
			String pd_spec_id = "";
			String channel_nbr = "";
			String action_cd = "";
			String returnValue = "";
			String pd_inst_id = "";
			String serv_spec_id = "";
			Document document = saxreader.read(source);
			//System.out.println(xml);
			List list = document.content();//.elementTextTrim("partyInfo");
			Iterator iter = list.iterator();
			//int i = 0;
			if(iter.hasNext()){
				Element element = (Element) iter.next();
				staff_id = (element.attribute("soStaffId").getValue() == null) ? "" : element.attribute("soStaffId").getValue();
				channel_nbr = (element.attribute("soChannelNbr").getValue() == null) ? "" : element.attribute("soChannelNbr").getValue();
				soDate = (element.attribute("soDate").getValue() == null) ? "" : element.attribute("soDate").getValue();
				custId = (element.element("partyInfo").element("custId").getText() == null) ? "" : element.element("partyInfo").element("custId").getText();
				//publicValue += staff_id + "|" + channel_nbr + "|" + soDate + "|" + custId +"|";
				List offerAction = document.selectNodes("/OrderList/actionMsgs/offerAction");
				Iterator iter1 = offerAction.iterator();
				//int i = 0;
				//System.out.println(actionMsgs.size());
				while(iter1.hasNext()){
					Element element1 = (Element) iter1.next();
					po_inst_id = (element1.attribute("offerId").getValue() == null) ? "" : element1.attribute("offerId").getValue();
					action_cd = (element1.attribute("boActionCd").getValue() == null) ? "" : element1.attribute("boActionCd").getValue();
					offer_spec_id = (element1.attribute("offerSpecId").getValue() == null) ? "" : element1.attribute("offerSpecId").getValue();
					if(action_cd.equals("S1")){
						//i++;
						//System.out.println(i);
						if(element1.element("new") != null){
							if(element1.element("new").element("offerMembers") != null){
								//System.out.println(i+"*********"+element1.element("new").element("offerMembers").element("offerMember").element("memberSpecId").getText());
								pd_spec_id = (element1.element("new").element("offerMembers").element("offerMember").element("memberSpecId").getText() == null) ? "" : element1.element("new").element("offerMembers").element("offerMember").element("memberSpecId").getText();
								//offer_spec_id = (element1.element("new").element("offerBaseInfo").element("offerSpecId").getText() == null) ? "" : element1.element("new").element("offerBaseInfo").element("offerSpecId").getText();
								pd_inst_id = (element1.element("new").element("offerMembers").element("offerMember").element("memberId").getText() == null) ? "" : element1.element("new").element("offerMembers").element("offerMember").element("memberId").getText();
								//returnValue = publicValue + po_inst_id + "|" + action_cd + "|" + offer_spec_id + "|" + pd_inst_id + "|" + pd_spec_id;
								returnValue = pd_inst_id + "|" + custId + "|" + po_inst_id + "|" + pd_spec_id + "|" + "" + "|" + action_cd + "|" + soDate + "|" + staff_id + "|" + soDate + "|" + channel_nbr + "|" + "";
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
					if(action_cd.equals("1")){
						//returnValue = publicValue + "" + "|" + action_cd + "|" + "" + "|" + pd_inst_id + "|" + pd_spec_id;
						returnValue = pd_inst_id + "|" + custId + "|" + "" + "|" + pd_spec_id + "|" + "" + "|" + action_cd + "|" + soDate + "|" + staff_id + "|" + soDate + "|" + channel_nbr + "|" + "";
						orderRepository.offer(returnValue);
					}else if(action_cd.equals("7")){
						//System.out.println("7****"+id);
						//System.out.println(xml);
						if(element2.element("new") != null){
							if(element2.element("new").element("prodServ") != null){
								if(element2.element("new").element("prodServ").element("serv") != null){
									if(element2.element("new").element("prodServ").element("serv").element("servInfo") != null){
										serv_spec_id = (element2.element("new").element("prodServ").element("serv").element("servInfo").element("servSpecId").getText() == null) ? "" : element2.element("new").element("prodServ").element("serv").element("servInfo").element("servSpecId").getText();
										//returnValue = publicValue + po_inst_id + "|" + action_cd + "|" + offer_spec_id + "|" + pd_inst_id + "|" + pd_spec_id;
										returnValue = pd_inst_id + "|" + custId + "|" + po_inst_id + "|" + pd_spec_id + "|" + serv_spec_id + "|" + action_cd + "|" + soDate + "|" + staff_id + "|" + soDate + "|" + channel_nbr + "|" + "";
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
}
