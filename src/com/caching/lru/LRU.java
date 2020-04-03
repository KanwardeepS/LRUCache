package com.caching.lru;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

class LRUCacheItem {

	private HashMap<String, Object> lruList;
	private Stack<String> stackOrder;
	private int capacity;

	public LRUCacheItem( int capacity ) {
		this.capacity = capacity;
		this.lruList = new HashMap<String, Object>( capacity );
		this.stackOrder = new Stack<String>();
	}

	public void add( String key, Object value ) {
		String remove = null;
		if ( lruList.containsKey( key ) || this.capacity < lruList.size() + 1 ) {
			if ( lruList.containsKey( key ) ) {
				remove = key;
			}
			else {
				remove = this.stackOrder.firstElement();
			}
			this.stackOrder.removeElement( remove );
			this.lruList.remove( remove );
		}
		this.lruList.put( key, value );
		this.stackOrder.add( key );
	}

	public Stack<String> get() {
		return this.stackOrder;
	}

	public Object get( String key ) {
		Object value = lruList.get( key );
		if(null==value) {
			System.out.println("-1");
		}else {
			System.out.println(value);
		}
		add( key, value );
		return value;
	}
	
	public Object remove( String key ) {
		Object value = lruList.get( key );
		if(null==value) {
			System.out.println("-1");
		}else {
			System.out.println(value);
		}
		delete( key );
		return value;
	}

	private void delete( String p_key ) {
		String remove = null;
		if ( lruList.containsKey( p_key ) || this.capacity < lruList.size() + 1 ) {
			if ( lruList.containsKey( p_key ) ) {
				remove = p_key;
			}
			else {
				remove = this.stackOrder.firstElement();
			}
			this.stackOrder.removeElement( remove );
			this.lruList.remove( remove );
		}
		
	}

	public void evict() {
		String key = this.stackOrder.pop();
		this.lruList.remove( key );
	}


}

public class LRU {

	public static void main( String[] args ) {
		
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		String input;
		LRUCacheItem lru = new LRUCacheItem( 20 );
		try {
			input = br.readLine();
			while ( input != null ) {
				if(input.contains( "add" )) {
					String [] arrVal = input.split( " " );
					lru.add( arrVal[1], arrVal[2] );
				}else if(input.contains( "get" )) {
					String [] arrVal = input.split( " " );
					lru.get( arrVal[1]);
				}else if(input.contains( "remove" )) {
					String [] arrVal = input.split( " " );
					lru.remove( arrVal[1]);
				}else if(input.contains( "evict" )) {
					lru.evict( );
				}else if(input.contains( "exit" )) {
					System.exit( 0 );
				}
				input = br.readLine();
			}
		}
		catch ( Exception l_ex ) {
			System.out.println("-1");
			
		}

	}

}
