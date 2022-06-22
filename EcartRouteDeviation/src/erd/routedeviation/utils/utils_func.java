package erd.routedeviation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.geom.Point2D;
	
public class utils_func {
	
	// TODO 1 - Read and parse CSV or TXT file
	@SuppressWarnings({ "unused", "resource" })
	public static Map<String, ArrayList<String>> get_wp_from_csv_file(final String filename) throws Exception
	{		
		File doc = new File(filename);
		Scanner obj = new Scanner(doc);

		Map<String, ArrayList<String>> map_wp = new HashMap<String, ArrayList<String>>();
		
		BufferedReader br = new BufferedReader(new FileReader(doc));

		  String line;
		  while ((line = br.readLine()) != null)
		  {
			  ArrayList<String> paire_lat_lon = new ArrayList<String>();
			  paire_lat_lon.add(line.split(",")[1]);
			  paire_lat_lon.add(line.split(",")[2]);
			  map_wp.put(line.split(",")[0], paire_lat_lon);
		  }

//		while (obj.hasNextLine()) //reader file and parser
//		{
//			String line = obj.nextLine();
//			ArrayList<String> paire_lat_lon = new ArrayList<String>();
//			paire_lat_lon.add(line.split(",")[1]);
//			paire_lat_lon.add(line.split(",")[2]);
//			map_wp.put(line.split(",")[0], paire_lat_lon);
//		}
		
		//Test affichage infos du fichier CSV
		//for (String wp : map_wp.keySet()) System.out.println(wp + " : " + map_wp.get(wp));
		
		return map_wp;
	}	
	
	// TODO 2 - Converter Lat/Lon to DMS
	
	//dms to lat
	public static double convert_DMS_to_lat_degree(final String lat)
	{
		ArrayList<Integer> list_lat = new ArrayList<Integer>(3);
		String lat_temp=lat;
		String m="";
		list_lat.add(Integer.parseInt(lat_temp.split("°")[0]));
		lat_temp = lat_temp.substring(lat_temp.indexOf("°")+1, lat_temp.length());
		m+=lat_temp.charAt(0);
		m+=lat_temp.charAt(1);
		list_lat.add(Integer.parseInt(m));
		lat_temp = lat_temp.substring(3, lat_temp.length());
		list_lat.add(Integer.parseInt(lat_temp.split("'")[0]));
		
		//attention aux centiemes de secondes *0.6 ajoute pour reconvertir en secondes normales
		return (lat_temp.charAt(lat_temp.length()-1))=='N' ?
				(list_lat.get(0)+list_lat.get(1)/60.+(list_lat.get(2)*0.6)/3600.)
				: (list_lat.get(0)+list_lat.get(1)/60.+(list_lat.get(2)*0.6)/3600.)*(-1);	
	}
	
	//dms to lon
	public static double convert_DMS_to_lon_degree(final String lon)
	{
		ArrayList<Integer> list_lon = new ArrayList<Integer>(3);
		String lon_temp=lon;
		String m="";
		list_lon.add(Integer.parseInt(lon_temp.split("°")[0]));
		lon_temp = lon_temp.substring(lon_temp.indexOf("°")+1, lon_temp.length());
		m+=lon_temp.charAt(0);
		m+=lon_temp.charAt(1);
		list_lon.add(Integer.parseInt(m));
		lon_temp = lon_temp.substring(3, lon_temp.length());
		list_lon.add(Integer.parseInt(lon_temp.split("'")[0]));
				
		//attention aux centiemes de secondes *0.6 ajoute pour reconvertir en secondes normales
		return (lon_temp.charAt(lon_temp.length()-1))=='E' ?
				(list_lon.get(0)+list_lon.get(1)/60.+(list_lon.get(2)*0.6)/3600.)
				: (list_lon.get(0)+list_lon.get(1)/60.+(list_lon.get(2)*0.6)/3600.)*(-1);	
	}
	
	//Integer 10 base to binary String - Facultative function
	public static String tobin(int nb)
	{
		String str = "";
		while (nb!=0)
		{
			str+=String.valueOf(nb%2);
			nb=nb/2;
		}
		return str;
	}
	
	
	// TODO 3 - Converter of Lat/Lon to Vector2D point par projection de type Mercator 
	
	public static Point2D.Double convert_lat_lon_to_2DPoint(final double lat, final double lon, final int tx, final int ty)
	{
		//TEST 1
		double x = (lon+180)*(tx/360);
		double latRad = lat*Math.PI/180;
		double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
		double y = (ty/2)-(tx*mercN/(2*Math.PI));
		
		//TEST 2
//		double x = tx*Math.toRadians(lon);
//		double scale = x/lon;
//		double y = 180.0/Math.PI * Math.log(Math.tan(Math.PI/4.0 + Math.toRadians(lat)/2.0)) * scale;
//		System.out.println("x : " + x + " et y : " + y);
		
		Point2D.Double p = new Point2D.Double(x,y);
		return (tx==0||ty==0)?null:p;
	}
	
	
	// TODO 4 - Calcul par projection orthogonal d'une position Vector2D à une droite (AB), nommé P, return Vector2D point P_proj
	
	public static Point2D.Double get_2Dpoint_orthogonal_projection(Point2D.Double P, Point2D.Double A, Point2D.Double B)
	{
		double P_proj_A = (((P.x-A.x)*(A.x-B.x))+((P.y-A.y)*(A.y-B.y)))/A.distance(B);
		Point2D.Double P_proj = new Point2D.Double(A.x+((P_proj_A/A.distance(B))*(A.x-B.x)), A.y+((P_proj_A/A.distance(B))*(A.y-B.y)));
		return P_proj;
	}
	
	// TODO 5 - Trouver indice du waypoint le plus proche du point P à suivre sur la Route en fonction de la distance de projection
	public static int get_indice_next_waypoint(Point2D.Double P,  ArrayList<Point2D.Double> list_wp)
	{
		int ind_min_dist=0;
		double min_dist=list_wp.get(0).distance(P);
		for (int i=1; i<list_wp.size();i++) 
			if (min_dist>list_wp.get(i).distance(P)) {ind_min_dist=i; min_dist=list_wp.get(i).distance(P);}
		
		if (ind_min_dist==0) return 1;
		else if (ind_min_dist==list_wp.size()-1) return ind_min_dist;
		else
		{
			if (get_2Dpoint_orthogonal_projection(P, list_wp.get(ind_min_dist-1), list_wp.get(ind_min_dist)).distance(P)
				<get_2Dpoint_orthogonal_projection(P, list_wp.get(ind_min_dist), list_wp.get(ind_min_dist+1)).distance(P))
			{
				return ind_min_dist;
			}
			else return ind_min_dist+1;
		}
	}
	
}
