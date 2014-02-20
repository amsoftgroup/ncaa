package com.sombrerosoft.ncaa.hibernate;

import java.util.ArrayList;

public class TeamStats {

	private int wins = 0;
	private int losses = 0;
	private double winavg = 0;
	private double lossavg = 0;
	private ArrayList<Integer> winarray = new ArrayList<Integer>();
	private ArrayList<Integer> lossarray = new ArrayList<Integer>();
	
	// teams that we beat, but who won against other teams that beat us
	private ArrayList<Integer> win_loss_array = new ArrayList<Integer>();
	
	// teams that beat us, but lost against other teams we beat
	private ArrayList<Integer> loss_win_array = new ArrayList<Integer>();
	
	
	public double getWinavg() {
		return winavg;
	}
	public void setWinavg(double winavg) {
		this.winavg = winavg;
	}
	public double getLossavg() {
		return lossavg;
	}
	public void setLossavg(double lossavg) {
		this.lossavg = lossavg;
	}
	
	public void addWins(Integer i){
		winarray.add(i);
	}
	public ArrayList<Integer> getWinsArray(){
		return winarray;
	}
	public void addLosses(Integer i){
		lossarray.add(i);
	}
	public ArrayList<Integer> getLossArray(){
		return lossarray;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public ArrayList<Integer> getWin_loss_array() {
		return win_loss_array;
	}
	public void addWin_loss_array(Integer teamid) {
		this.win_loss_array.add(teamid);
	}
	public ArrayList<Integer> getLoss_win_array() {
		return loss_win_array;
	}
	public void addLoss_win_array(Integer teamid) {
		this.loss_win_array.add(teamid);
	}
}
