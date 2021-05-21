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

import models.Meeting;

public class MeetingsParser {
	public List<Meeting> getMeetings(String filePath ) throws IOException {
       try( Stream<String> lines = Files.lines(Paths.get(filePath))){
        return lines
                .map(line -> line.split(",|\\;",-1))
                .map(toMeeting())
                .collect(Collectors.toList());
       }
    }

	private Function<String[], Meeting> toMeeting() {
		return fields -> {
			int id = Stream.of(fields[0])
					.map(toInt->Integer.parseInt(toInt))
					.findFirst().get();
			
//			LocalDateTime startDate = Stream.of(fields[1])
//					.map(a->todate(a))
//					.findFirst().get();
			LocalDateTime startDate = todate(fields[1]);
			
//			LocalDateTime endDate = Stream.of(fields[2])
//					.map(a->todate(a))
//					.findFirst().get();
			LocalDateTime endDate = todate(fields[2]);
			
			Meeting meeting =new Meeting(id, startDate, endDate);
			for(int i=3;i<=(fields.length-1);i++){
				int participants=Stream.of(fields[i])
						.map(toInt->Integer.parseInt(toInt))
						.findFirst().get();
				meeting.addParticipantId(participants);
			}
			
			return meeting;
    };
}
//	private Set<Integer> combine(Set<Integer>... sets){
//        Set<Integer> collection = new HashSet<>();
//        Stream.of(sets).forEach(collection::addAll);
//     
//        return collection;
//    }
	public static LocalDateTime todate(String a) {
		DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		if(a.length()!=0) {
			LocalDateTime d= LocalDateTime.parse(a, formatter);
			return d;
		}else return LocalDateTime.of(0000,01,01,00,00);
		
	}
}
