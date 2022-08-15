import React from 'react';
import {PluginClient, usePlugin, createState, useValue, Layout, DataTableColumn, createTablePlugin} from 'flipper-plugin';

type Data = {
  timestamp: string;
  type: string;
  value: string;
};

type Events = {
  newData: Data;
};


const columns: DataTableColumn<Data>[] = [
  {
    key: 'timestamp',
    title: 'Timestamp',
    width: 150,
  },
  {
    key: 'type',
    title: 'Type',
    width: 150,
  },
    {
    key: 'value',
    title: 'Value',
  },
];

module.exports = createTablePlugin<Data>({
  columns,
  method: 'newData',
  key: 'timestamp',
});



// If you want a custom UI rather tan a table, use the code below

// Read more: https://fbflipper.com/docs/tutorial/js-custom#creating-a-first-plugin
// API: https://fbflipper.com/docs/extending/flipper-plugin#pluginclient
// export function plugin(client: PluginClient<Events, {}>) {
//   const data = createState<Record<string, Data>>({}, {persist: 'data'});

//   client.onMessage('newData', (newData) => {
//     data.update((draft) => {
//       draft[newData.timestamp] = newData;
//     });
//   });

//   client.addMenuEntry({
//     action: 'clear',
//     handler: async () => {
//       data.set({});
//     },
//     accelerator: 'ctrl+l',
//   });

//   return {data};
// }

// // Read more: https://fbflipper.com/docs/tutorial/js-custom#building-a-user-interface-for-the-plugin
// // API: https://fbflipper.com/docs/extending/flipper-plugin#react-hooks
// export function Component() {
//   const instance = usePlugin(plugin);
//   const data = useValue(instance.data);

//   return (
//     <Layout.ScrollContainer>
//       {Object.entries(data).map(([id, d]) => (
//         <pre key={id} data-testid={id}>
//           {JSON.stringify(d)}
//         </pre>
//       ))}
//     </Layout.ScrollContainer>
//   );
// }

