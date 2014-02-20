package com.sombrerosoft.ncaa.hibernate;

// Generated Feb 15, 2014 11:57:40 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * SeasonsId generated by hbm2java
 */
public class Seasons implements java.io.Serializable {

	private String season;
	private String years;
	private Date dayzero;
	private String regionW;
	private String regionX;
	private String regionY;
	private String regionZ;

	public Seasons() {
	}

	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getYears() {
		return this.years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public Date getDayzero() {
		return this.dayzero;
	}

	public void setDayzero(Date dayzero) {
		this.dayzero = dayzero;
	}

	public String getRegionW() {
		return this.regionW;
	}

	public void setRegionW(String regionW) {
		this.regionW = regionW;
	}

	public String getRegionX() {
		return this.regionX;
	}

	public void setRegionX(String regionX) {
		this.regionX = regionX;
	}

	public String getRegionY() {
		return this.regionY;
	}

	public void setRegionY(String regionY) {
		this.regionY = regionY;
	}

	public String getRegionZ() {
		return this.regionZ;
	}

	public void setRegionZ(String regionZ) {
		this.regionZ = regionZ;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Seasons))
			return false;
		Seasons castOther = (Seasons) other;

		return ((this.getSeason() == castOther.getSeason()) || (this
				.getSeason() != null && castOther.getSeason() != null && this
				.getSeason().equals(castOther.getSeason())))
				&& ((this.getYears() == castOther.getYears()) || (this
						.getYears() != null && castOther.getYears() != null && this
						.getYears().equals(castOther.getYears())))
				&& ((this.getDayzero() == castOther.getDayzero()) || (this
						.getDayzero() != null && castOther.getDayzero() != null && this
						.getDayzero().equals(castOther.getDayzero())))
				&& ((this.getRegionW() == castOther.getRegionW()) || (this
						.getRegionW() != null && castOther.getRegionW() != null && this
						.getRegionW().equals(castOther.getRegionW())))
				&& ((this.getRegionX() == castOther.getRegionX()) || (this
						.getRegionX() != null && castOther.getRegionX() != null && this
						.getRegionX().equals(castOther.getRegionX())))
				&& ((this.getRegionY() == castOther.getRegionY()) || (this
						.getRegionY() != null && castOther.getRegionY() != null && this
						.getRegionY().equals(castOther.getRegionY())))
				&& ((this.getRegionZ() == castOther.getRegionZ()) || (this
						.getRegionZ() != null && castOther.getRegionZ() != null && this
						.getRegionZ().equals(castOther.getRegionZ())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSeason() == null ? 0 : this.getSeason().hashCode());
		result = 37 * result
				+ (getYears() == null ? 0 : this.getYears().hashCode());
		result = 37 * result
				+ (getDayzero() == null ? 0 : this.getDayzero().hashCode());
		result = 37 * result
				+ (getRegionW() == null ? 0 : this.getRegionW().hashCode());
		result = 37 * result
				+ (getRegionX() == null ? 0 : this.getRegionX().hashCode());
		result = 37 * result
				+ (getRegionY() == null ? 0 : this.getRegionY().hashCode());
		result = 37 * result
				+ (getRegionZ() == null ? 0 : this.getRegionZ().hashCode());
		return result;
	}

}
