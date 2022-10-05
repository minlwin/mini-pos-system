import { environment } from "src/environments/environment";

export abstract class AbstractService {
  basePath(path:string) {
    return `${environment.api}/${path}`
  }
}
