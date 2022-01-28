package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();   //여러 쓰레드에서 동시 접근 시 해쉬 맵 사용 X  (ConcurrentHashMap 사용)
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); //굳이 ArrayList로 감싸는 이유는 값이 변경되도 원래 값이 변경되지 않도록
    }

    public void update(Long itemId, Item updateParam) { //원래는 Item 객체를 사용하지 않고 DTO 에 실제 사용할 것들만 만들어서 사용해야 함 (id, setter 이런거 없이..) 중복 vs 명확성 -> 명확성!!
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
