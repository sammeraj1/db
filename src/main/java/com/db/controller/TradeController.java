package com.db.controller;

import java.text.ParseException;

import com.db.model.Trade;
import com.db.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TradeController {


	private TradeService tradeService;

	@Autowired
	public TradeController( TradeService tradeService ) {
		this.tradeService = tradeService;
	}


	@RequestMapping(value="/process-trades", method=RequestMethod.POST)
	public ResponseEntity<Trade> getBillDetails(@RequestBody Trade trade) throws ParseException {

		Trade tradeResponse=tradeService.processTrade(trade);
		return new ResponseEntity<>(tradeResponse, HttpStatus.OK);
	}
}
