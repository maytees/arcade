import {defineConfig, passthroughImageService} from 'astro/config';
import starlight from '@astrojs/starlight';

import tailwind from "@astrojs/tailwind";

// https://astro.build/config
export default defineConfig({
  image: {
    service: passthroughImageService()
  },
  integrations: [starlight({
    title: 'Arcade',
    customCss: [
      './src/tailwind.css'
    ],
    description: 'Minecraft survival minigames plugin. Bringing challenging tweaks for you and your friends to experience.',
    social: {
      github: 'https://github.com/maytees/arcade'
    },
    sidebar: [{
      label: 'Start Here!',
      items: [
      // Each item here is one entry in the navigation menu.
      {
        label: 'Introduction',
        slug: 'starthere/introduction'
      }, {
        label: 'Getting Started',
        slug: 'starthere/gettingstarted'
      }]
    }, {
      label: 'Minigames',
      items: [{
        label: 'Overview',
        slug: 'minigames/overview'
      }, {
        label: 'Item Rush',
        slug: 'minigames/itemrush'
      }, {
        label: 'Mob Rush',
        slug: 'minigames/mobrush'
      }, {
        label: 'Lava Rise',
        slug: 'minigames/lavarise'
      }, {
        label: 'Death Swap',
        slug: 'minigames/deathswap'
      }]
    }, {
      label: 'Global Settings',
      items: [{
        label: 'Overview',
        slug: 'globalsettings/overview'
      }, {
        label: 'World Border',
        slug: 'globalsettings/worldborder'
      }, {
        label: 'World Border Size',
        slug: 'globalsettings/bordersize'
      }, {
        label: 'Flight',
        slug: 'globalsettings/flight'
      }, {
        label: 'Dimensions',
        slug: 'globalsettings/dimensions'
      }, {
        label: 'PVP',
        slug: 'globalsettings/pvp'
      }]
    }]
  }), tailwind( {
        applyBaseStyles: false
      })
  ]
});