using Drink_Count.Models;
using Drink_Count.Extensions;
using SQLite;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Drink_Count.Data
{
    public class DrinkDatabase
    {
        static readonly Lazy<SQLiteAsyncConnection> lazyInitializer = new Lazy<SQLiteAsyncConnection>(() =>
        {
            return new SQLiteAsyncConnection(Constants.DatabasePath, Constants.Flags);
        });

        static SQLiteAsyncConnection Database => lazyInitializer.Value;
        static bool initialized = false;

        public DrinkDatabase()
        {
            InitializeAsync().SafeFireAndForget(false);
        }

        async Task InitializeAsync()
        {
            if (!initialized)
            {
                if (!Database.TableMappings.Any(m => m.MappedType.Name == typeof(Drink).Name))
                {
                    await Database.CreateTablesAsync(CreateFlags.None, typeof(Drink)).ConfigureAwait(false);
                }
                initialized = true;
            }
        }

        public Task<List<Drink>> GetItemsAsync()
        {
            return Database.Table<Drink>().ToListAsync();
        }

        public Task<Drink> GetItemAsync(int id)
        {
            return Database.Table<Drink>().Where(i => i.Id == id).FirstOrDefaultAsync();
        }

        public Task<int> SaveItemAsync(Drink item)
        {
            if (item.Id != 0)
            {
                return Database.UpdateAsync(item);
            }
            else
            {
                return Database.InsertAsync(item);
            }
        }

        public Task<int> DeleteItemAsync(Drink item)
        {
            return Database.DeleteAsync(item);
        }
    }
}
