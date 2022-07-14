import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.api.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

public class Project01_JSON {
    // Object(BookDto) --> JSON
    public static void main(String[] args) {
        BookDto dto = new BookDto("자바의 정석", 26000,  "남궁", 560);
        Gson g = new Gson();
        String json = g.toJson(dto);
        System.out.println("json = " + json);
        // {"title":"자바의 정석","price":26000,"author":"남궁","page":560}

        // JSON(문자열) --> Object(BookDto)
        BookDto dto1 = g.fromJson(json, BookDto.class);
        System.out.println("dto1 = " + dto1);
        //BookDto{title='자바의 정석', price=26000, author='남궁', page=560}

        //Object(List<BookDto>) --> JSON(String)
        List<BookDto> lst = new ArrayList<BookDto>();
        lst.add(new BookDto("자바의 정석1", 21000,  "남궁", 111));
        lst.add(new BookDto("자바의 정석2", 22000,  "남궁", 222));
        lst.add(new BookDto("자바의 정석3", 23000,  "남궁", 333));
        lst.add(new BookDto("자바의 정석4", 24000,  "남궁", 444));

        String lstJson = g.toJson(lst);
        System.out.println("lstJson = " + lstJson);
        //[{"title":"자바의 정석1","price":21000,"author":"남궁","page":111},
        // {"title":"자바의 정석2","price":22000,"author":"남궁","page":222},
        // {"title":"자바의 정석3","price":23000,"author":"남궁","page":333},
        // {"title":"자바의 정석4","price":24000,"author":"남궁","page":444}]

        // Json(문자열) --> Object(List<BookDto>)
        List<BookDto> lst1 = g.fromJson(lstJson, new TypeToken<List<BookDto>>(){}.getType());
        for (BookDto a : lst1) {
            System.out.println(a);
            //BookDto{title='자바의 정석1', price=21000, author='남궁', page=111}
            //BookDto{title='자바의 정석2', price=22000, author='남궁', page=222}
            //BookDto{title='자바의 정석3', price=23000, author='남궁', page=333}
            //BookDto{title='자바의 정석4', price=24000, author='남궁', page=444}
        }
    }
}
