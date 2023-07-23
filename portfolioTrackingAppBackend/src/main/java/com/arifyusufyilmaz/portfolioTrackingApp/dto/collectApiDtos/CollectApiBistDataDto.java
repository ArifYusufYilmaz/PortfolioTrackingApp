package com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos;

import java.util.List;

public class CollectApiBistDataDto {
    private boolean success;
    private List<BistAssetDto> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<BistAssetDto> getResult() {
        return result;
    }

    public void setResult(List<BistAssetDto> result) {
        this.result = result;
    }
}
