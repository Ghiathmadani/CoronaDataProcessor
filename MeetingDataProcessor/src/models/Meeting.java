package models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Meeting {
	private int id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Set<Integer> participantsIds;
	
	public Meeting(int id, LocalDateTime startDate, LocalDateTime endDate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	
	public void addParticipantId(Integer id){
      if (participantsIds == null) {
          participantsIds = new HashSet<>();
      }
      participantsIds.add(id);
  }
	
	public Set<Integer> getParticipantIds() {
		return participantsIds;
	}
	
	@Override
	public String toString() {
		return "Meeting [id=" + id + ", start_date=" + startDate + ", end_date=" + endDate + ", participants="
				+ participantsIds + "]";
	}
}
