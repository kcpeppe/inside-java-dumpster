/*
 *
 */
package inside.dumpster.monitoring.event;

import inside.dumpster.bl.BusinessLogicService;
import inside.dumpster.client.Payload;
import inside.dumpster.monitoring.TransactionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joakim Nordstrom joakim.nordstrom@oracle.com
 */
public class ServiceInvocation extends TransactionEvent {
  public String protocol;
  public String dstPort;
  public String dstPackets;
  public int dstBytes;
  public String srcPackets;
  public String data;
  public int srcBytes;
  public Class<? extends BusinessLogicService> serviceClass;

  /**
   * Extract info from payload and set on this event.
   * @param payload
   */
  @Override
  public void registerPayloadData(Payload payload) {
    super.registerPayloadData(payload);
    try {
      this.data = payload.getInputStream() != null ? new String(payload.getInputStream().readAllBytes(), StandardCharsets.UTF_8):"";
    } catch (IOException ex) {
      
    }
    this.protocol = payload.getProtocol();
    this.dstPort = payload.getDstPort();
    this.dstPackets = payload.getDstPackets();
    this.dstBytes = payload.getDstBytes();
    this.srcPackets = payload.getSrcPackets();
    this.srcBytes = payload.getSrcBytes();

  }
}
