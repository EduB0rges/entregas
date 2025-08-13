package br.com.entregas.aplicacao.servico.listener;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventoListener implements CacheEventListener<Object, Object> 
{
	private static final Logger LOGGER = LoggerFactory.getLogger( CacheEventoListener.class );

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) 
	{
		LOGGER.debug( "Cache event: {} - Key: {}, Old Value: {}, New Value: {}",
				event.getType(), 
				event.getKey(), 
				event.getOldValue(), 
				event.getNewValue() );
	}
}
