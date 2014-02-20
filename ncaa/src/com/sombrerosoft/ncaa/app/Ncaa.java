package com.sombrerosoft.ncaa.app;

import java.util.ArrayList;

import com.sombrerosoft.ncaa.dao.NcaaManager;
import com.sombrerosoft.ncaa.hibernate.RegularSeasonResults;
import com.sombrerosoft.ncaa.hibernate.SampleSubmission;
import com.sombrerosoft.ncaa.hibernate.Seasons;
import com.sombrerosoft.ncaa.hibernate.TeamStats;
import com.sombrerosoft.ncaa.hibernate.Teams;
import com.sombrerosoft.ncaa.hibernate.TourneyResults;
import com.sombrerosoft.ncaa.hibernate.TourneySeeds;
import com.sombrerosoft.ncaa.hibernate.TourneySlots;

public class Ncaa {
	
	public Ncaa(){
		
	}
	
	public static void main(String[] args){
		
		Ncaa ncaa = new Ncaa();
		ncaa.test();

	}
	
	public void run_sim(){
		
	}
	
	public void test(){
		
		
		int win = 0;
		int loss = 0;
		int teamid = 591;
		String season = "R";
		
		ArrayList<Teams> teams = NcaaManager.getTeams();
		System.out.println("NumTeams:" + teams.size());
		
		Teams team = NcaaManager.getTeam(teamid);
		System.out.println("team:" + team.getName());
		
		ArrayList<Seasons> seasons =  NcaaManager.getSeasons();
		System.out.println("NumSeasons:" + seasons.size());
		
		ArrayList<RegularSeasonResults> results = NcaaManager.getRegularSeasonResults();
		System.out.println("RegularSeasonResults:" + results.size());
		
		/*
		for (int i = 0; i < results.size(); i++){
			System.out.println("	results:" + results.get(i).getLscore() + " "  + results.get(i).getWscore());
		}
		*/
		ArrayList<TourneyResults> tourneyresults = NcaaManager.getTourneyResults();
		System.out.println("tourneyresults:" + tourneyresults.size());
		
		ArrayList<TourneySeeds> seeds =  NcaaManager.getTourneySeeds();
		System.out.println("seeds:" + seeds.size());
		
		ArrayList<TourneySlots> slots =  NcaaManager.getTourneySlots();
		System.out.println("slots:" + slots.size());
		
		ArrayList<SampleSubmission> sample_submission = NcaaManager.getSampleSubmissions();
		System.out.println("sample_submission:" + sample_submission.size());
		

		
		/*
		ArrayList<RegularSeasonResults> team_season = NcaaManager.getScoresByTeamIdandSeason(teamid, season);

	
		for (int i = 0; i < team_season.size(); i++){
			
			if (team_season.get(i).getWteam() == teamid){
				System.out.println(teamid + "+ wins against " + team_season.get(i).getLteam());
				win++;
			}else{
				System.out.println(teamid + "- loses against " + team_season.get(i).getWteam());
				loss++;
			}
		}
		
		System.out.println("	win:" + win);
		System.out.println("	loss:" + loss);
		*/
		TeamStats stats = NcaaManager.getTeamStats(teamid, season);
		System.out.println("	wins:" + stats.getWins() + " @" + stats.getWinavg());
		System.out.println("	losses:" + stats.getLosses() + "@ " + stats.getLossavg());
		
		// getWinsArray has all teams we beat
		for (int i = 0; i < stats.getWinsArray().size(); i++){
			System.out.println(teamid + "	win against:" + stats.getWinsArray().get(i) );
			TeamStats ostats = NcaaManager.getTeamStats(stats.getWinsArray().get(i), season);
			System.out.println("		(" + ostats.getWins() +  "/" + ostats.getLosses() + ")"); 
			System.out.println("		(" + ostats.getWinavg() + "/" + ostats.getLossavg() + ")");
		}
		
		for (int i = 0; i < stats.getLossArray().size(); i++){
			System.out.println(teamid + "	loss against:" + stats.getLossArray().get(i));
			TeamStats ostats = NcaaManager.getTeamStats(stats.getLossArray().get(i), season);
			System.out.println("		(" + ostats.getWins() + "/" + ostats.getLosses() + ")"); 
			System.out.println("		(" + ostats.getWinavg() + "/" +  ostats.getLossavg() + ")"); 
		}
		
/*
		for (int i = 0; i < stats.getLoss_win_array().size(); i++){
			System.out.println("		stats.getLoss_win_array():" + i + " " + stats.getLoss_win_array().get(i)); 
		}
		for (int i = 0; i < stats.getWin_loss_array().size(); i++){
			System.out.println("		stats.getWin_loss_array():" + i + " " + stats.getWin_loss_array().get(i)); 	
		}
*/
	}

}
