package com.db.service;


import com.db.model.Trade;
import com.db.repository.TradeRepository;
import com.db.service.TradeService;
import junit.framework.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(SpringRunner.class)
public class TradeServiceTest {

	@InjectMocks
	private TradeService tradeService;

	@Mock
	private TradeRepository tradeRepository;

	@Before
	public void setup() {
		this.tradeService = new TradeService(tradeRepository);
	}
	@Test
	public void testProcessTrade() throws ParseException {
		Trade trade=new Trade();
		trade.setTradeId("T1");
		trade.setVersion(1);
		trade.setMaturityDate("20/08/2020");
		when(tradeRepository.findTradeByTradeId(trade.getTradeId())).thenReturn(java.util.Optional.of(trade));
		Assert.assertEquals(null, tradeService.processTrade(trade));
	}
}
