package com.sombrerosoft.ncaa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sombrerosoft.db.postgres.Database;
import com.sombrerosoft.ncaa.hibernate.RegularSeasonResults;
import com.sombrerosoft.ncaa.hibernate.SampleSubmission;
import com.sombrerosoft.ncaa.hibernate.Seasons;
import com.sombrerosoft.ncaa.hibernate.TeamStats;
import com.sombrerosoft.ncaa.hibernate.Teams;
import com.sombrerosoft.ncaa.hibernate.TourneyResults;
import com.sombrerosoft.ncaa.hibernate.TourneySeeds;
import com.sombrerosoft.ncaa.hibernate.TourneySlots;
import com.sombrerosoft.ncaa.util.Util;

public class NcaaManager {

	
	@SuppressWarnings("unchecked")
	public static TeamStats getTeamStats(int teamid, String season){
		
		TeamStats stats = new TeamStats();
		int wins = 0;
		int losses = 0;
		int windif = 0;
		int lossdif = 0;
		double winavg = 0;
		double lossavg = 0;
		
		ArrayList<RegularSeasonResults> season_results = NcaaManager.getScoresByTeamIdandSeason(teamid, season);
		for (int i = 0; i < season_results.size(); i++){
			if (season_results.get(i).getWteam() == teamid){
				wins++;
				windif += season_results.get(i).getWscore() - season_results.get(i).getLscore();	
				stats.addWins(new Integer(season_results.get(i).getLteam()));				
			}else{
				losses++;
				lossdif += season_results.get(i).getWscore() - season_results.get(i).getLscore();
				stats.addLosses(new Integer(season_results.get(i).getWteam()));
			}	
		}
	
		/*
		// we beat team "ostats". did they beat a team that beat us?
		for (int i = 0; i < stats.getWinsArray().size(); i++){
			TeamStats ostats = NcaaManager.getTeamStats(season_results.get(i).getLteam(), season);
			for (int j = 0; j < ostats.getWinsArray().size(); j++){
				//System.out.println("-----ostats.getWinsArray().get(i))" + ostats.getWinsArray().get(j) );
				if (stats.getLossArray().contains(ostats.getWinsArray().get(j))){
					//System.out.println("stats.getLossArray().contains(ostats.getWinsArray().get(j)!!!");
					stats.addWin_loss_array(ostats.getWinsArray().get(j));
				}
			}
		}
		
		// we lost to team "ostats". did they lose to a team we beat?
		for (int i = 0; i < stats.getLossArray().size(); i++){
			TeamStats ostats = NcaaManager.getTeamStats(season_results.get(i).getWteam(), season);
			for (int j = 0; j < ostats.getLossArray().size(); j++){
				//System.out.println("-----ostats.getWinsArray().get(i))" + ostats.getWinsArray().get(j) );
				if (stats.getWinsArray().contains(ostats.getLossArray().get(j))){
					//System.out.println("stats.getLossArray().contains(ostats.getWinsArray().get(j)!!!");
					stats.addLoss_win_array(ostats.getLossArray().get(j));
				}
			}
		}
		*/
		winavg = windif/(double)season_results.size();
		lossavg = lossdif/(double)season_results.size();
		
		stats.setLosses(losses);
		stats.setWins(wins);
		stats.setWinavg(Util.round(winavg, 2));
		stats.setLossavg(Util.round(lossavg,2));
		
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	public static String getTeamnameById(int teamid){
		
		String result = null;
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "select name from teams where id = ?;";

			ps = c.prepareStatement(sql);
			ps.setInt(1, teamid);
			ResultSet rs = ps.executeQuery();

			if( rs.next() ){		
				result = rs.getString("name");
				
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<RegularSeasonResults> getScoresByTeamIdandSeason(int teamid, String season){
		
		ArrayList<RegularSeasonResults> results = new ArrayList<RegularSeasonResults>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "select distinct * from regular_season_results  where  (wteam = ? or lteam = ?) and season = ?";

			ps = c.prepareStatement(sql);
			ps.setInt(1, teamid);
			ps.setInt(2, teamid);
			ps.setString(3, season);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				RegularSeasonResults result = new RegularSeasonResults();
				result.setWteam(rs.getInt("wteam"));
				result.setWscore(rs.getInt("wscore"));
				result.setLteam(rs.getInt("lteam"));
				result.setLscore(rs.getInt("lscore"));
				result.setSeason(season);

				results.add(result);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<SampleSubmission> getSampleSubmissions(){
		
		ArrayList<SampleSubmission> results = new ArrayList<SampleSubmission>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM sample_submission order by pred";			
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				SampleSubmission result = new SampleSubmission();
				result.setId(rs.getString("id"));
				result.setPred(rs.getInt("pred"));
				results.add(result);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<TourneySlots> getTourneySlots(){
		
		ArrayList<TourneySlots> results = new ArrayList<TourneySlots>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM tourney_slots order by season";			
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				TourneySlots result = new TourneySlots();
				result.setSeason(rs.getString("season"));
				result.setSlot(rs.getString("slot"));
				result.setStrongseed(rs.getString("strongseed"));
				result.setWeakseed(rs.getString("weakseed"));
				results.add(result);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<TourneySeeds> getTourneySeeds(){
		
		ArrayList<TourneySeeds> results = new ArrayList<TourneySeeds>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM tourney_seeds order by season";			
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				TourneySeeds result = new TourneySeeds();
				result.setSeason(rs.getString("season"));
				result.setSeed(rs.getString("seed"));
				result.setTeam(rs.getInt("team"));
				results.add(result);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return results;
	}

	
	@SuppressWarnings("unchecked")
	public static ArrayList<TourneyResults> getTourneyResults(){
		
		ArrayList<TourneyResults> results = new ArrayList<TourneyResults>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM tourney_results order by season";			
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				TourneyResults result = new TourneyResults();
				result.setSeason(rs.getString("season"));
				result.setDaynum(rs.getInt("daynum"));
				result.setLscore(rs.getInt("lscore"));
				result.setWscore(rs.getInt("wscore"));
				result.setLteam(rs.getInt("lteam"));
				result.setWteam(rs.getInt("wteam"));
				result.setNumot(rs.getString("numot"));
				results.add(result);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<RegularSeasonResults> getRegularSeasonResults(){
		
		ArrayList<RegularSeasonResults> results = new ArrayList<RegularSeasonResults>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM regular_season_results order by season";			
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				RegularSeasonResults result = new RegularSeasonResults();
				result.setSeason(rs.getString("season"));
				result.setDaynum(rs.getInt("daynum"));
				result.setLscore(rs.getInt("lscore"));
				result.setWscore(rs.getInt("wscore"));
				result.setLteam(rs.getInt("lteam"));
				result.setWteam(rs.getInt("wteam"));
				result.setWloc(rs.getString("wloc"));
				result.setNumot(rs.getString("numot"));
				results.add(result);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return results;
	}


	@SuppressWarnings("unchecked")
	public static ArrayList<Seasons> getSeasons(){
		
		ArrayList<Seasons> seasons = new ArrayList<Seasons>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM seasons order by season";			
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				Seasons season = new Seasons();
				season.setDayzero(rs.getDate("dayzero"));
				season.setRegionW(rs.getString("regionW"));
				season.setRegionX(rs.getString("regionX"));
				season.setRegionY(rs.getString("regionY"));
				season.setRegionZ(rs.getString("regionZ"));
				season.setYears(rs.getString("years"));
				season.setSeason(rs.getString("season"));
				seasons.add(season);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return seasons;
	}

	
	@SuppressWarnings("unchecked")
	public static ArrayList<Teams> getTeams(){
		
		ArrayList<Teams> ets = new ArrayList<Teams>();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM teams order by id";			
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				Teams et = new Teams();
				et.setId(rs.getInt(1));
				et.setName(rs.getString(2));
				ets.add(et);
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return ets;
	}

	@SuppressWarnings("unchecked")
	public static Teams getTeam(int id){
		
		Teams team = new Teams();
		PreparedStatement ps =  null;
		Connection c = null;
		Database d = new Database();

		try {
			
			c = d.getConnection();
			String sql = "SELECT * FROM teams where id = ? order by id";			
			ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while( rs.next() ){		
				team.setId(rs.getInt(1));
				team.setName(rs.getString(2));
			}

			rs.close();
			ps.close();
			c.close();
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		
		return team;
	}
}
