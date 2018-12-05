package net.aicoder.speedcloud.business.app.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppBaseInfoEvent {

    private AppBaseInfoEventTopic topic;
    private Long tid;
    private AppBaseInfoVO vo;

}
