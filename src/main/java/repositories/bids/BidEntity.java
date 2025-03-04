package repositories.bids;

import repositories.auctions.AuctionEntity;
import repositories.users.UserEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "bids")
@NamedQuery(
    name = "Bids.findAll",
    query = "SELECT b FROM BidEntity b WHERE b.auction.id = :auctionId ORDER BY b.auction",
    hints = @QueryHint(name = "org.hibernate.cacheable",
    value = "true")
)
@NamedQuery(
    name = "Bids.byUser",
    query = "SELECT b FROM BidEntity b WHERE b.user.id = :userId ORDER BY b.auction",
    hints = @QueryHint(name = "org.hibernate.cacheable", value = "true")
)
@NamedQuery(
    name = "Bids.byUserAndAuction",
    query = "SELECT b FROM BidEntity b WHERE b.user.id = :userId AND b.auction.id = :auctionId ORDER BY b.auction",
    hints = @QueryHint(name = "org.hibernate.cacheable", value = "true")
)
@NamedNativeQuery(
    name="Bids.highestByAuction",
    query = "SELECT DISTINCT ON (b.auction_id) b.id, b.auction_id, b.user_id, b.bid_time, b.bid_amount "
        + "FROM bids b "
        + "WHERE b.auction_id = :auctionId "
        + "ORDER BY b.auction_id ASC, b.bid_amount DESC, b.bid_time ASC;", 
    resultClass = BidEntity.class
 )
@NamedNativeQuery(
    name="Bids.highestAllAuctions",
    query = "SELECT DISTINCT ON (b.auction_id) b.id, b.auction_id, b.user_id, b.bid_time, b.bid_amount "
            + "FROM bids b "
            + "ORDER BY b.auction_id ASC, b.bid_amount DESC, b.bid_time ASC;",
    resultClass = BidEntity.class
)
@Cacheable
public class BidEntity {
    @Id
    @SequenceGenerator(name = "bidsSequence", sequenceName = "bids_id_seq", schema = "auction", allocationSize = 1)
    @GeneratedValue(generator = "bidsSequence")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AuctionEntity auction;

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserEntity user;

    @Column(columnDefinition="timestamp")
    private LocalDateTime bid_time;

    @Column
    private int bid_amount;

    public BidEntity() {
    }

    public BidEntity(
        AuctionEntity auction,
        UserEntity user,
        LocalDateTime bid_time,
        int bid_amount
    ) {
        this.auction = auction;
        this.user = user;
        this.bid_time = bid_time;
        this.bid_amount = bid_amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuctionEntity getAuction() {
        return auction;
    }

    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getBidTime() {
        return bid_time;
    }

    public void setBidTime(LocalDateTime bid_time) {
        this.bid_time = bid_time;
    }


    public int getBidAmount() {
        return bid_amount;
    }

    public void setBidAmount(int bid_amount) {
        this.bid_amount = bid_amount;
    }
}
