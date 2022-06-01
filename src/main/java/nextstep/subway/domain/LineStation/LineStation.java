package nextstep.subway.domain.LineStation;

import nextstep.subway.domain.line.Line;
import nextstep.subway.domain.station.Station;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class LineStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    protected LineStation() {
    }

    private LineStation(Line line, Station station) {
        this.line = line;
        this.station = station;
    }

    public static LineStation create(Line line, Station station) {
        if (Objects.isNull(line) || Objects.isNull(station)) {
            throw new IllegalArgumentException("LineStation.create() Line 또는 Station이 null입니다.");
        }

        return new LineStation(line, station);
    }

    public Long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Station getStation() {
        return station;
    }

    public boolean isSameLine(Line line) {
        return Objects.equals(this.line.getId(), line.getId());
    }

    public boolean isSameStation(Station station) {
        return Objects.equals(this.station.getId(), station.getId());
    }
}
