/**
 * Dato que se va a transmitir con el EventEmitter
 */
export interface ShipData {
    id: number;
    name: string;
    destination: string;
    position: { lat: number; lng: number };
  }
  