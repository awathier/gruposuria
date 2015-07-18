package br.com.gruposuria.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gruposuria.entity.Cidade;

@FacesConverter(value = "ufConverter")
public class CidadeConverter implements Converter {

	private static Map<Object, String> entities = new WeakHashMap<Object, String>();

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object entity) {
		synchronized (entities) {
			if (!entities.containsKey(entity)) {
				String uuid = UUID.randomUUID().toString();
				entities.put(entity, uuid);
				return uuid;
			} else {
				return entities.get(entity);
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String uuid) {
		for (Entry<Object, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(uuid)) {
				return entry.getKey();
			}
		}
		Cidade cidade = new Cidade();
		cidade.setNome(uuid);
		return cidade;
	}

}