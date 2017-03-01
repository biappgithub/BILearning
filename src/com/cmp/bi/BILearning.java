package com.cmp.bi;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.unicacorp.interact.treatment.optimization.IClientArgs;
import com.unicacorp.interact.treatment.optimization.IInteractSession;
import com.unicacorp.interact.treatment.optimization.ILearningConfig;
import com.unicacorp.interact.treatment.optimization.ILearningContext;
import com.unicacorp.interact.treatment.optimization.IOffer;
import com.unicacorp.interact.treatment.optimization.LearningException;
import com.unicacorp.interact.treatment.optimization.impl.v2.LearningServiceFactory;
import com.unicacorp.interact.treatment.optimization.impl.v2.NaiveBayesImpl;
import com.unicacorp.interact.treatment.optimization.v2.ILearning;
import com.unicacorp.interact.treatment.optimization.v2.ITreatment;

public class BILearning implements ILearning {
	
	private static Logger logger = null;
	@Override
	public void initialize(ILearningConfig config, boolean debug) throws LearningException {	
		
		logger = Logger.getLogger(BILearning.class);
		/* Test GIT*/
		/*
		 runtime environment reserved parameters
			 UACIEventID
			 UACIEventName
			 UACIInteractiveChannelID
			 UACIInteractiveChannelName
			 UACIInteractionPointID
			 UACIInteractionPointName
			 UACISessionID		 
		 */
		
		/**
         * Get the built-in implementation
         */
        //ILearning learning = LearningServiceFactory.getInstance().getNaiveBayesImpl();
        ILearning learning = LearningServiceFactory.getInstance().getDefaultNonLearningBehaviorImpl();
        
        /**
         * Call the built-in implementation's initialize method
         */
        learning.initialize(config, debug);
		
	}

	@Override
	public void logEvent(ILearningContext context, IOffer offer, IClientArgs clientArgs, IInteractSession session, boolean debug) throws LearningException {
		/**
         * Get the built-in implementation
         */
        //ILearning learning = LearningServiceFactory.getInstance().getNaiveBayesImpl();
		ILearning learning = LearningServiceFactory.getInstance().getDefaultNonLearningBehaviorImpl();
        
        /**
         * Call the built-in implementation's logEvent method
         */
        learning.logEvent(context, offer, clientArgs, session, debug);
		
	}

	@Override
	public List<ITreatment> optimizeRecommendList(List<ITreatment> recList, IClientArgs clientArgs, IInteractSession session, boolean debug) throws LearningException {
		
		logger.info("**** Calling optimizeRecommendList for BILearning *****");		
		//logger.info("=== Start Call UACIEventID ===" + session.getSessionData().getValue("UACIEventID"));	
		//logger.info("=== Start Call UACIInteractiveChannelID ===" + session.getSessionData().getValue("UACIInteractiveChannelID"));	
		logger.info("=== Start Call UACIInteractiveChannelName ===" + session.getSessionData().getValue("UACIInteractiveChannelName"));	
		//logger.info("=== Start Call UACIInteractionPointID ===" + session.getSessionData().getValue("UACIInteractionPointID"));	
		//logger.info("=== Start Call UACIInteractionPointName ===" + session.getSessionData().getValue("UACIInteractionPointName"));	
		//logger.info("=== Start Call UACISessionID ===" + session.getSessionData().getValue("UACISessionID"));	
		
		if(session.getSessionData().getValue("UACIInteractiveChannelName").equals("MAGIC_SCREEN_PRE")){
			logger.info("**** Get the external learning implementation *****");
			
			List<ITreatment> result = new ArrayList<ITreatment>();
			List<Double> finalScores =  new ArrayList<Double>();
			
			List<ITreatment> resultNormal = new ArrayList<ITreatment>();
			List<Double> finalScoresNormal =  new ArrayList<Double>();
			
			
			int normalFlag = 0;
			int elseFlag = 0;
			
			ITreatment treatmentA = null;
			Double maxScoreA = 0.0; 
			int countA = 0;
			
			ITreatment treatmentB = null;
			Double maxScoreB = 0.0; 
			int countB = 0;
			
			ITreatment treatmentC = null;
			Double maxScoreC = 0.0; 
			int countC = 0;
			
			ITreatment treatmentD = null;
			Double maxScoreD = 0.0; 
			int countD = 0;
			
			ITreatment treatmentE = null;
			Double maxScoreE = 0.0; 
			int countE = 0;
			
			ITreatment treatmentF = null;
			Double maxScoreF = 0.0; 
			int countF = 0;
			
			ITreatment treatmentG = null;
			Double maxScoreG = 0.0; 
			int countG = 0;
									
			for(int x=0;x<recList.size();x++){
				
	    		ITreatment treatment = recList.get(x);
	    		IOffer offer = treatment.getOffer();
	    		String pOfferStrategy = (String) offer.getOfferAttributes().getValue("Product_type");    		
	    		//String pOfferStrategy = treatment.getAttributeParameterizationMap().get("OfferStrategy");
	    		Double LearningScore = treatment.getLearningScore();
	    		logger.info("**** Check getOfferCode *****" + offer.getOfferCode()); 
	    		logger.info("**** Check Package Type *****" + pOfferStrategy);  
	    		logger.info("**** Check getLearningScore *****" + treatment.getLearningScore());
	    		logger.info("**** Check getMarketerScore *****" + treatment.getMarketerScore());
	    		logger.info("**** Check getPredicateScore *****" + treatment.getPredicateScore());
	    		
	    		if(pOfferStrategy.replace("\"", "").equals("Info_Handset")){
	    			if(LearningScore > maxScoreA){
	    				treatmentA = recList.get(x);
	    				maxScoreA = LearningScore;
	    				countA++;
	    			}
	    			
	    		}else if(pOfferStrategy.replace("\"", "").equals("Info_Network")){
	    			if(LearningScore > maxScoreB){
	    				treatmentB = recList.get(x);
	    				maxScoreB = LearningScore;
	    				countB++;
	    			}
	    			
		    	}else if(pOfferStrategy.replace("\"", "").equals("Info_PretoPost")){
	    			if(LearningScore > maxScoreC){
	    				treatmentC = recList.get(x);
	    				maxScoreC = LearningScore;
	    				countC++;
	    			}
	    			
		    	}else if(pOfferStrategy.replace("\"", "").equals("Info_Service")){
	    			if(LearningScore > maxScoreD){
	    				treatmentD = recList.get(x);
	    				maxScoreD = LearningScore;
	    				countD++;
	    			}
	    			
		    	}else if(pOfferStrategy.replace("\"", "").equals("PK_Fee_Sell")){
	    			if(LearningScore > maxScoreE){
	    				treatmentE = recList.get(x);
	    				maxScoreE = LearningScore;
	    				countE++;
	    			}
	    			
		    	}else if(pOfferStrategy.replace("\"", "").equals("PK_Free_Give")){
	    			if(LearningScore > maxScoreF){
	    				treatmentF = recList.get(x);
	    				maxScoreF = LearningScore;
	    				countF++;
	    			}
	    			
		    	}else if(pOfferStrategy.replace("\"", "").equals("PK_Refill")){
	    			if(LearningScore > maxScoreG){
	    				treatmentG = recList.get(x);
	    				maxScoreG = LearningScore;
	    				countG++;
	    			}
	    			
		    	}else if(pOfferStrategy.replace("\"", "").equals("Normal")){
		    		normalFlag++;
		    		resultNormal.add(recList.get(x));
		    		finalScoresNormal.add(LearningScore);
		    		logger.info("**** Found Normal Offer Return Only Normal Offer *****"); 
		    	
		    	}else if(pOfferStrategy.replace("\"", "").equals("Else")){
		    		elseFlag++;
		    		logger.info("**** Found Else Offer Return Only Else Offer *****"); 
		    		
		    	}else{
		    		logger.info("**** Found Else Offer  *****"); 
		    	}
	    												
	    	}
					
			if(countA > 0){
				logger.info("**** Check Final Offer Info_Handset *****" + treatmentA.getOffer().getOfferCode()); 
				result.add(treatmentA);
				finalScores.add(maxScoreA);
			}
			if(countB > 0){
				logger.info("**** Check Final Offer Info_Network *****" + treatmentB.getOffer().getOfferCode()); 
				result.add(treatmentB);
				finalScores.add(maxScoreB);
			}
			if(countC > 0){
				logger.info("**** Check Final Offer Info_PretoPost *****" + treatmentC.getOffer().getOfferCode()); 
				result.add(treatmentC);
				finalScores.add(maxScoreC);
			}
			if(countD > 0){
				logger.info("**** Check Final Offer Info_Service *****" + treatmentD.getOffer().getOfferCode()); 
				result.add(treatmentD);
				finalScores.add(maxScoreD);
			}
			if(countE > 0){
				logger.info("**** Check Final Offer PK_Fee_Sell *****" + treatmentE.getOffer().getOfferCode()); 
				result.add(treatmentE);
				finalScores.add(maxScoreE);
			}
			if(countF > 0){
				logger.info("**** Check Final Offer PK_Free_Give *****" + treatmentF.getOffer().getOfferCode()); 
				result.add(treatmentF);
				finalScores.add(maxScoreF);
			}
			if(countG > 0){
				logger.info("**** Check Final Offer PK_Refill *****" + treatmentG.getOffer().getOfferCode()); 
				result.add(treatmentG);
				finalScores.add(maxScoreG);
			}
			
			if(countA > 0 || countB > 0 || countC > 0 || countD > 0 || countE > 0 || countF > 0 || countG > 0){
				List<ITreatment> rankedTreatments = NaiveBayesImpl.rankOffers(result, finalScores);
				return rankedTreatments;
				
			}else if(normalFlag > 0){
				List<ITreatment> rankedTreatments = NaiveBayesImpl.rankOffers(resultNormal, finalScoresNormal);
				return rankedTreatments;
				
			}else{
				/**
		         * Get the built-in implementation
		         */
		        //ILearning learning = LearningServiceFactory.getInstance().getNaiveBayesImpl();
				ILearning learning = LearningServiceFactory.getInstance().getDefaultNonLearningBehaviorImpl();
		        
		        /**
		         * Call the built-in implementation's optimizeRecommendList method
		         */
		        return learning.optimizeRecommendList(recList, clientArgs, session, debug);
			}
			
			/*
			if(normalFlag > 0){
				
				ILearning learning = LearningServiceFactory.getInstance().getDefaultNonLearningBehaviorImpl();		       
		        return learning.optimizeRecommendList(recList, clientArgs, session, debug);
			}else{
				List<ITreatment> rankedTreatments = NaiveBayesImpl.rankOffers(result, finalScores);
				return rankedTreatments;
			}
			*/
						
		}else{
			for(int x=0;x<recList.size();x++){
				
	    		ITreatment treatment = recList.get(x);
	    		IOffer offer = treatment.getOffer(); 
	    		logger.info("**** Get the built-in implementation *****" + offer.getOfferCode());	
	    		logger.info("**** Check getLearningScore *****" + treatment.getLearningScore());
	    		logger.info("**** Check getMarketerScore *****" + treatment.getMarketerScore());
	    		logger.info("**** Check getPredicateScore *****" + treatment.getPredicateScore());
			}
			/**
	         * Get the built-in implementation
	         */
	        //ILearning learning = LearningServiceFactory.getInstance().getNaiveBayesImpl();
			ILearning learning = LearningServiceFactory.getInstance().getDefaultNonLearningBehaviorImpl();
	        
	        /**
	         * Call the built-in implementation's optimizeRecommendList method
	         */
	        return learning.optimizeRecommendList(recList, clientArgs, session, debug);
			
		}		
	}

	@Override
	public void reinitialize(ILearningConfig config, boolean debug) throws LearningException {
		/**
         * Get the built-in implementation
         */
        //ILearning learning = LearningServiceFactory.getInstance().getNaiveBayesImpl();
        ILearning learning = LearningServiceFactory.getInstance().getDefaultNonLearningBehaviorImpl();
        /**
         * Call the built-in implementation's reinitialize method
         */
        learning.reinitialize(config, debug);
		
	}

	@Override
	public void shutdown(ILearningConfig config, boolean debug) throws LearningException {
		/**
         * Get the built-in implementation
         */
        //ILearning learning = LearningServiceFactory.getInstance().getNaiveBayesImpl();
		ILearning learning = LearningServiceFactory.getInstance().getDefaultNonLearningBehaviorImpl();
        
        /**
         * Call the built-in implementation's optimizeRecommendList method
         */
        learning.shutdown(config, debug);
		
	}

}
