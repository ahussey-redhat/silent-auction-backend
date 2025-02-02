package repositories.auctions;

import java.time.LocalDateTime;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "auctions")
@NamedQuery(name = "AuctionEntity.findAll", query = "SELECT a FROM AuctionEntity a ORDER BY a.item_name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "false"))
@Cacheable
public class AuctionEntity {

    @Id
    @SequenceGenerator(name = "auctionsSequence", sequenceName = "auctions_id_seq", schema = "auction", allocationSize = 1)
    @GeneratedValue(generator = "auctionsSequence")
    private Integer id;

    @Column(length = 255, unique = true)
    private String item_name;

    @Column(length = 255)
    private String description;

    @Column(columnDefinition="timestamp")
    private LocalDateTime auction_start;

    @Column(columnDefinition="timestamp")
    private LocalDateTime auction_end;

    @Column
    private Integer starting_bid;

    @Column(length = 255)
    private String image_path;

    public AuctionEntity() {
    }

    public AuctionEntity(
        String item_name,
        String description,
        LocalDateTime auction_start,
        LocalDateTime auction_end,
        Integer starting_bid,
        String image_path
    ) {
        this.item_name = item_name;
        this.description = description;
        this.auction_start = auction_start;
        this.auction_end = auction_end;
        this.starting_bid = starting_bid;
        this.image_path = image_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAuctionStart() {
        return auction_start;
    }

    public void setAuctionStart(LocalDateTime auction_start) {
        this.auction_start = auction_start;
    }

    public LocalDateTime getAuctionEnd() {
        return auction_end;
    }

    public void setAuctionEnd(LocalDateTime auction_end) {
        this.auction_end = auction_end;
    }

    public Integer getStartingBid() { return starting_bid; }

    public void setStartingBid(Integer starting_bid) { this.starting_bid = starting_bid; }

    public String getImagePath() {
        return image_path;
    }

    public void setImagePath(String image_path) {
        this.image_path = image_path;
    }
}