//package cn.techwolf.experiment.common.mqTest;
//
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.remoting.common.RemotingUtil;
//
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// */
//public class Consumer {
//
//
//    private static AtomicLong cmd = new AtomicLong(0);
//
//    private DefaultMQPushConsumer consumer;
//
//    public void init() {
//        try {
////            consumer = new DefaultMQPushConsumer();
////            consumer.setInstanceName(RemotingUtil.getLocalAddress() + "@" + UtilAll.getPid() + "@" + getCmd());
////            String namesrvAdress = envConfigService.getRocketMQNamesrvAdress();
////            consumer.setNamesrvAddr(namesrvAdress);
////            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
////            consumer.setMessageModel(MessageModel.CLUSTERING);
////            consumer.setMaxReconsumeTimes(5);
////            consumer.setConsumeMessageBatchMaxSize(1);
////            consumer.subscribe(PushMessageConstants.TOPIC_BW_QUERY_ZP_EXCHANGE_INFO, "*");
////            consumer.registerMessageListener(new MessageListenerConcurrently() {
////                @Override
////                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
////                    for (MessageExt messageExt : messageExtList) {
////                        try {
////                            String content = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
////                            if (StringUtils.isNotBlank(content)) {
////                                QueryZpExchangeInfoMessage zpExchangeInfoMessage = JSON.parseObject(content, QueryZpExchangeInfoMessage.class);
////
////                                boolean result;
////                                if (zpExchangeInfoMessage.getExchangeType() == UserExchangeInfo.EXCHANGE_TYPE_PHONE) {
////                                    // 查询Boss手机号，并展示给店长
////                                    result = bwExchangeService.sendZpPhoneToDianzhang(zpExchangeInfoMessage.getDzUserId(), zpExchangeInfoMessage.getDzIdentity(),
////                                            zpExchangeInfoMessage.getZpUserId(), zpExchangeInfoMessage.getZpIdentity());
////                                } else {
////                                    // 查询Boss微信号，并展示给店长
////                                    result = bwExchangeService.sendZpWeixinToDianzhang(zpExchangeInfoMessage.getDzUserId(), zpExchangeInfoMessage.getDzIdentity(),
////                                            zpExchangeInfoMessage.getZpUserId(), zpExchangeInfoMessage.getZpIdentity());
////                                }
////                                LoggerManager.info(String.format("QueryZpExchangeInfoListener.consumeMessage::zpExchangeInfoMessage=%s,result=%s", JSON.toJSONString(zpExchangeInfoMessage), result));
////                                if (!result) {
////                                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
////                                }
//                            }
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//            });
//            consumer.start();
//            LoggerManager.info("QueryZpExchangeInfoListener消费者服务已启动");
//        } catch (MQClientException e) {
//            LoggerManager.error("QueryZpExchangeInfoListener消费者服务启动失败", e);
//        }
//
//    }
//
//    public void destroy() {
//        consumer.shutdown();
//        System.out.println("QueryZpExchangeInfoListener消费者服务已关闭...");
//    }
//
//
//    public static long getCmd() {
//        return cmd.incrementAndGet();
//    }
//
//}
