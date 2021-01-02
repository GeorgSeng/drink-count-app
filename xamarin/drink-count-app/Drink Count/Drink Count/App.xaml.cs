using Drink_Count.Data;
using Drink_Count.Services;
using Xamarin.Forms;

namespace Drink_Count
{
    public partial class App : Application
    {
        private static DrinkDatabase database;

        public App()
        {
            InitializeComponent();

            MainPage = new AppShell();
        }

        public static DrinkDatabase Database {
            get
            {
                if (database is null)
                    database = new DrinkDatabase();
                return database;
            }
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
