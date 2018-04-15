package es.sinjava.data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutInterceptor extends AbstractPhaseInterceptor<Message> {

	private static final Logger LOG = LoggerFactory.getLogger(OutInterceptor.class);

	public OutInterceptor(String phase) {
		super(Phase.MARSHAL);
	}

	public OutInterceptor() {
		super(Phase.MARSHAL);
	}

	@Override
	public void handleMessage(Message outMessage) throws Fault {
		LOG.debug("Init ");
		Map<String, List<String>> headers = (Map<String, List<String>>) outMessage.get(Message.PROTOCOL_HEADERS);

		if (headers == null) {
			headers = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
			headers.put("Access-Control-Allow-Methods", Arrays.asList("POST, GET, PUT, OPTIONS, DELETE"));
			headers.put("Access-Control-Max-Age", Arrays.asList("1508"));
			headers.put("Access-Control-Allow-Origin", Arrays.asList("*"));
			headers.put("Access-Control-Allow-Headers",
					Arrays.asList("Origin, X-Requested-With, Content-Type, Accept"));
			outMessage.put(Message.PROTOCOL_HEADERS, headers);
		}
	}
}
