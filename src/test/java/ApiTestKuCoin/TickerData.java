package ApiTestKuCoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class TickerData {
    private String symbol;
    private String symbolName;
    private String buy;
    private String sell;
    private String bestBidSize;
    private String bestAskSize;
    private String changeRate;
    private String changePrice;
    private String high;
    private String low;
    private String vol;
    private String volValue;
    private String last;
    private String averagePrice;
    private String takerFeeRate;
    private String makerFeeRate;
    private String takerCoefficient;
    private String makerCoefficient;
}
