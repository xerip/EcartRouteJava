package erd.routedeviation.demo;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import erd.routedeviation.utils.utils_func;

public class maindemo {

	/*
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		// Creation window of project
		mainwindow Route_window = new mainwindow();
		
		// Default Window Parameters
		int width=1000, height=500;
		
		Route_window.setTitle("Calcul/Démonstration Route"); //
		Route_window.setSize(width,height); //taille fenetre par defaut
		Route_window.setLocationRelativeTo(null); //centrage de la fenetre par rapport a l'ecran
		
		// TODO B1 - Creation tableau Haut Gauche
		// TODO B2 - Creation Canvas Haut droite
		// TODO B3 - Creation labels/lineEdit bas gauche = entree pos porteur
		// TODO B4 - Creation labels/lineEdit bas droite = sortie resultat ecart route et next waypoint
		
		//partie Test
		Map<String, ArrayList<String>> datas_wp = utils_func.get_wp_from_csv_file("C:\\Users\\Pierre.MATTIOLI\\Desktop\\data.txt");

		ArrayList<ArrayList<String>> wp_values = new ArrayList<ArrayList<String>>(datas_wp.values());
		ArrayList<String> wp_keys = new ArrayList<String>(datas_wp.keySet());
		ArrayList<Point2D.Double> list_points = new ArrayList<Point2D.Double>();
		for (int i=0; i<datas_wp.size();i++)
		{
			double _x=utils_func.convert_DMS_to_lat_degree(wp_values.get(i).get(0));
			double _y=utils_func.convert_DMS_to_lon_degree(wp_values.get(i).get(1));
			//System.out.println(wp_keys.get(i));
			
			list_points.add(utils_func.convert_lat_lon_to_2DPoint(_x, _y, width, height));
		}
		
		double lat_porteur = utils_func.convert_DMS_to_lat_degree("39°27.51'N");
		double lon_porteur = utils_func.convert_DMS_to_lon_degree("007°01.36'E");
		Point2D.Double porteur_point = utils_func.convert_lat_lon_to_2DPoint(lat_porteur, lon_porteur, width, height);
		
		System.out.println("Le next WP est " + wp_keys.get(utils_func.get_indice_next_waypoint(porteur_point, list_points)));
	}

}
