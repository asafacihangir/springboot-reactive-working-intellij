# Fire-and-Forget

Fire-and-forget is an optimization of request/response that is useful when a response is not needed. It allows for significant performance optimizations, not just in saved network usage by skipping the response, but also in client and server processing time as no bookkeeping is needed to wait for and associate a response or cancellation request.(Fire-and-forget, response gerekmediğinde yararlı olan bir request/response optimizasyonudur. Fire-and-Forget  önemli performans optimizasyonlarına olanak sağlar.Ayrıca bir response veya iptal request’ini beklemek ve ilişkilendirmek için herhangi bir defter tutma gerekmediği için istemci ve sunucu işlem süresinde de optimizasyon sağlar.)

This interaction model is useful for use cases that support lossiness, such as non-critical event logging.(Bu etkileşim modeli, kritik olmayan olay günlüğü tutma gibi kaybolmayı destekleyen kullanım durumları için kullanışlıdır.)

Usage can be thought of like this:
```Java
Future<Void> completionSignalOfSend = socketClient.fireAndForget(message);
```

```Java
/**
 * A contract providing different interaction models for <a
 * href="https://github.com/RSocket/reactivesocket/blob/master/Protocol.md">RSocket protocol</a>.
 */
public interface RSocket extends Availability, Closeable {

  /**
   * Fire and Forget interaction model of {@code RSocket}.
   *
   * @param payload Request payload.
   * @return {@code Publisher} that completes when the passed {@code payload} is successfully handled, otherwise errors.
   */
  Mono<Void> fireAndForget(Payload payload);
}
```

- An optimization of Request-Response when a response isn’t necessary 
- Significant efficiencies
  - Networking(no ack)
  - Client/Server processsing(immediate release of resources)
- Non-critical event logging 

The fireAndForget method is primarily used for one-way push notifications. The return type is defined as Mono <Void>. Returning Mono <Void> means that no data is received from the fireAndForget method. 
