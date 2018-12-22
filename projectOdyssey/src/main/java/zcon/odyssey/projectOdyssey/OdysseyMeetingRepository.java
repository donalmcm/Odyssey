package zcon.odyssey.projectOdyssey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OdysseyMeetingRepository extends JpaRepository<OdysseyMeeting, Long> {
    List<OdysseyMeeting> findByOdyssey(Odyssey odyssey);
}
