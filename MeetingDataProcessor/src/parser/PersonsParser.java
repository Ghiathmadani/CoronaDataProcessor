package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Person;

public class PersonsParser {
	
	 public List<Person> getPersons(String filePath ) throws IOException {
	       try( Stream<String> lines = Files.lines(Paths.get(filePath))){
	        return lines
	                .map(line -> line.split(",",-1))
	                .map(toPerson())
	                .collect(Collectors.toList());
	       }
	    }

	private Function<String[], Person> toPerson() {
		return fields -> {
            int id = Stream.of(fields[0]).map(toInt->Integer.parseInt(toInt)).findFirst().get();

            LocalDateTime lastPositiveCheck = Stream.of(fields[1])
//            		.filter(text->text.length() != 0)
            		.map(a->todate(a))//LocalDateTime::parse
                    .findFirst().get();
            Person person =new Person(id);
            person.setLast_positive_check(lastPositiveCheck);
            return person;
        };
	}
	public static LocalDateTime todate(String a) {
		DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		if(a.length()!=0) {
			LocalDateTime d= LocalDateTime.parse(a, formatter);
			return d;
		}else return LocalDateTime.of(0000,11,11,11,11);
		
	}
}
