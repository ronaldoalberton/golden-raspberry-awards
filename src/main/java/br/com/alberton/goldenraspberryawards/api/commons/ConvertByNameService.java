package br.com.alberton.goldenraspberryawards.api.commons;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (15/01/22)
 */
public interface ConvertByNameService<T> extends IService<T> {

    T createByName(String name);

    default Set<T> convertByListNames(String[] names) {

        Set<T> list = new HashSet<>();

        for (String name : names) {

            if (name == null || name.isEmpty()) {
                continue;
            }

            name = name.trim();

            Optional<T> optional = this.findByName(name);

            if (optional.isPresent()) {

                list.add(optional.get());

            } else {

                T domain = this.save(this.createByName(name));

                list.add(domain);

            }

        }

        return list;

    }

}
