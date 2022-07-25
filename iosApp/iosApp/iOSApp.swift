import SwiftUI
import shared

@main
struct iOSApp: App {

  private func setupDbAndSync() {
    Db.shared.defaultDriver()

    let networkService = NetworkService()
    print("Sync Starting")
    Syncer.shared.syncNow(networkService: networkService) {
      print("Sync Complete")
    }
  }

  init() {
    self.setupDbAndSync()
  }
	var body: some Scene {
		WindowGroup {
      HomeScreen()
		}
	}
}
