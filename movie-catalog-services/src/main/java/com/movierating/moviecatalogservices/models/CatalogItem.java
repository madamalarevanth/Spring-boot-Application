package com.movierating.moviecatalogservices.models;

public class CatalogItem {

		private String name;
		private String desc;
		private int rating;
		
		
		public CatalogItem(String name, String desc, int i) {
			super();
			this.name = name;
			this.desc = desc;
			this.rating = i;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
}
