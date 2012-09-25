/**
 * Question
 * 
 * Let’s say that you have 25 horses, and you want to pick the fastest 3 horses out of those 25. 
 * In each race, only 5 horses can run at the same time because there are only 5 tracks. 
 * What is the minimum number of races required to find the 3 fastest horses without using a stopwatch?
 * @author lch
 *
 */

public class SpotHorse {

	public int matchcount=0;
	
	//insertion sorting  and matchcount increment
	public Horse[] giddyap(Horse[] horses,int start,int total){
		for (int i = start+1; i < start+total; i++) {
			Horse slowest=horses[i];
			int j=i;
			
			while(j>start&&horses[j-1].getSpeed()<slowest.getSpeed()){
				horses[j]=horses[j-1];
				j=j-1;
			}
			horses[j]=slowest; 
		}
		
		matchcount++;
		return horses;
	}
	
	//find max and matchcount increment
	public int spotFastest(Horse[] horses){
		int fastestno=0;
		for (int i = 0; i < horses.length; i++) {
			if(horses[i].getSpeed()>horses[fastestno].getSpeed())
				fastestno=i;
		}
		matchcount++;
		return fastestno;
		
	}
	
	/**
	 * 
	 * This method not only support pick out of 25 horses but also arbitrary horses.
	 * In main,it's a test to pick the fastest 3 horses  of 25 horses. 
	 * 
	 * @param allHorses
	 * @param spotCount arbitrary ,max is sqrt(horses count)
	 * @return horses array with expected num.
	 */
	public Horse[] spot(Horse[] allHorses,Integer spotCount){
		Horse[] h=new Horse[spotCount];
		Integer total=allHorses.length;
		Integer grouptotal=(int) Math.sqrt(total);
		
		for (int i = 0; i < allHorses.length;i=i+grouptotal ) {
			allHorses=giddyap(allHorses,i,grouptotal);
		}
		
		Horse[] _temp=new Horse[grouptotal];
		Integer[] _ps=new Integer[grouptotal];//offset value
		for (int i = 0; i < _ps.length; i++) {
			_ps[i]=0;
		}
		
		for (int i = 0; i < h.length; i++) {
			for (int j=0,k = 0; k < _temp.length; j=j+grouptotal,k++) {
				_temp[k]=allHorses[j+_ps[k]];
			}
			
			int fastest=spotFastest(_temp);
			h[i]=_temp[fastest];
			_ps[fastest]++;
			
		}
		
		return h;
	}
	
	public static void main(String[] args) {
		Horse[] horses=new Horse[25];
		
		//init 25 horses
		for (int i = 0; i < horses.length; i++) {
			String name="JACKHORSE"+i;// name 
			horses[i]=new Horse(name,(int)(Math.random()*1000));//random speed
		}
		
		
		SpotHorse sh=new SpotHorse();
		//call func to pick the fastest 3 horses  of 25 horses. 
		Horse[] three_fastest =sh.spot(horses,3);
		
		for (int i = 0; i < three_fastest.length; i++) {
			System.out.println("name:"+three_fastest[i].getName()+"__speed:"+three_fastest[i].getSpeed());
		}
		System.out.println("least match:"+sh.matchcount);
		
	}
}
